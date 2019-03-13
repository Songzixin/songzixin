package com.bawie.shuaxin;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telecom.Call;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author：asus
 * @E-mail： 945574298@163.com
 * @Date：2019/3/13 18:59
 * @Description：描述信息
 */
public class Utils {
     public static  boolean isNetConnected(Context context){
         if(context!=null){
             ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
             NetworkInfo networkInfo = manager.getActiveNetworkInfo();
             if(networkInfo!=null){
                 return networkInfo.isAvailable();
             }
         }
         return false;
     }
     public static  String Httpget(String strurl){
         try {
             URL url=new URL(strurl);
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setConnectTimeout(5000);
           connection.setReadTimeout(5000);
           connection.setRequestMethod("GET");
             InputStream stream = connection.getInputStream();
             InputStreamReader in = new InputStreamReader(stream);
             BufferedReader reader = new BufferedReader(in);
             StringBuffer buffer = new StringBuffer();
             String str="";
             while((str=reader.readLine())!=null){
                        buffer.append(str);
             }
            return buffer.toString();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
     public interface  CallBackString{
         void getDta(String s);
     }
     public static  void Asyntask(String str, final CallBackString callBackString){
          new AsyncTask<String, Void, String>() {
              @Override
              protected String doInBackground(String... strings) {
                  return Httpget(strings[0]);
              }

              @Override
              protected void onPostExecute(String s) {
                  super.onPostExecute(s);
                  callBackString.getDta(s);
              }
          }.execute(str);
     }
}
