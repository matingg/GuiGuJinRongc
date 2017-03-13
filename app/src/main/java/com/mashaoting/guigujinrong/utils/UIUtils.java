package com.mashaoting.guigujinrong.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.mashaoting.guigujinrong.command.MyApplication;

/**
 * Created by 麻少亭 on 2017/3/12.
 */

public class UIUtils {

    public  static Context getContext(){
        return  MyApplication.getContext();
    }

    public  static View getView( int layoutid){
        Log.e("TAG", "UIUtils getView()"+00000000000000000000000);
        return  View.inflate(getContext() , layoutid,null);

    }

    public  static int getColor(int color){
        return  getContext().getResources().getColor(color);
    }

    public static String[] getStringArray(int stringid){
        return  getContext().getResources().getStringArray(stringid);
    }

    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    public static  int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static void runOnUiThread(Runnable runnable) {
        //比较pid来判断是不是在主线程
        if (MyApplication.getThreadid()==android.os.Process.myPid()){
            runnable.run();
        }else{
            //给handler发送一个runnable
            MyApplication.getHandler().post(runnable);
        }
    }
}
