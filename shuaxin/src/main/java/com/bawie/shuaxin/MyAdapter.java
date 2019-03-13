package com.bawie.shuaxin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.shuaxin.bean.One;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * @Author：asus
 * @E-mail： 945574298@163.com
 * @Date：2019/3/13 19:41
 * @Description：描述信息
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<One> list;
    private Context context;

    public MyAdapter(ArrayList<One> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder viewHolder=null;
          if(convertView==null){
              convertView = View.inflate(context, R.layout.item, null);
              viewHolder  = new ViewHolder();
              viewHolder.imageView = convertView.findViewById(R.id.image);
              viewHolder.textView = convertView.findViewById(R.id.textview);
               convertView.setTag(viewHolder);
          }
          else{
              viewHolder = (ViewHolder) convertView.getTag();
          }
        One one = list.get(position);
          viewHolder.textView.setText(one.getTitle());
        Glide.with(context).load(one.getThumbnail_pic_s()).into(viewHolder.imageView);
        return convertView;
    }
    class ViewHolder{
 private TextView  textView;
 private ImageView imageView;
    }
}
