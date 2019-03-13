package com.bawie.shuaxin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bawie.shuaxin.bean.JsonBean;
import com.bawie.shuaxin.bean.One;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView pull;
    private MyAdapter myAdapter;
    private ArrayList<One> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull = findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setPullToRefreshOverScrollEnabled(true);
        Utils.Asyntask("http://www.xieast.com/api/news/news.php?page=1", new Utils.CallBackString() {
            @Override
            public void getDta(String s) {
                Gson gson=new Gson();
                JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                ArrayList<One> data = jsonBean.getData();
                MyAdapter myAdapter = new MyAdapter(data, MainActivity.this);
                pull.setAdapter(myAdapter);
                pull.onRefreshComplete();
            }
        });
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Utils.Asyntask("http://www.xieast.com/api/news/news.php?page=1", new Utils.CallBackString() {



                    @Override
                    public void getDta(String s) {
                        Gson gson=new Gson();
                        JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                        data = jsonBean.getData();
                        myAdapter = new MyAdapter(data, MainActivity.this);
                        pull.setAdapter(myAdapter);
                        pull.onRefreshComplete();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Utils.Asyntask("http://www.xieast.com/api/news/news.php?page=1", new Utils.CallBackString() {
                    @Override
                    public void getDta(String s) {
                        Gson gson=new Gson();
                        JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                        ArrayList<One> data1 = jsonBean.getData();
                        data.addAll(data1);
                        pull.setAdapter(myAdapter);

                        pull.onRefreshComplete();
                    }
                });

            }
        });
    }
}
