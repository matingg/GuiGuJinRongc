package com.mashaoting.guigujinrong.command;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

/**
 * Created by 麻少亭 on 2017/3/12.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Thread mainThread;
    private static int threadid;
    private static Handler handler;

    @Override           // 1、onCreate（） 在创建应用程序时创建
    public void onCreate() {
        super.onCreate();
        context = this;
        threadid = android.os.Process.myPid();
        handler = new Handler();

        //初始化  崩溃信息管理者
//        CrashHandler.getInstance().init();

    }

    /*
    *  2、onTerminate（） 在模拟环境下执行。当终止应用程序对象时调用，不保证一定被调用，
       当程序是被内核终止以便为其他应用程序释放资源，
          那么将不会提醒，并且不调用应用程序的对象的onTerminate方法而直接终止进程。*/
    @Override  //
    public void onTerminate() {
        super.onTerminate();
    }

    /*3、onLowMemory（） 低内存时执行。
    好的应用程序一般会在这个方法里面释放一些不必要的资源来应付当后台程序已经终止，
    前台应用程序内存还不够时的情况。*/
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /*4、onConfigurationChanged（Configuration newConfig） 配置改变时触发这个方法。*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /*

    5、onTrimMemory（int level）程序在进行内存清理时执行​*/
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getThreadid() {
        return threadid;
    }

    public static Handler getHandler() {
        return handler;
    }


}
