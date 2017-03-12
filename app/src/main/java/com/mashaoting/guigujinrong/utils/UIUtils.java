package com.mashaoting.guigujinrong.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by 麻少亭 on 2017/3/12.
 */

public class UIUtils {

    public  static Context getContext(){
        return  MyApplication.getContext();
    }

    public  static View getView( int layoutid){
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
}
