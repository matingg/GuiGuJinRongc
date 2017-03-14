package com.mashaoting.guigujinrong.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.utils.UIUtils;

/**
 * Created by 麻少亭 on 2017/3/13.
 */

public abstract class LoadingPager extends FrameLayout {


    private Context context;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;
    private LayoutParams params;
    private ResultState resultState;

    private int STATE_LOADING = 1; //加载中
    private int STATE_ERROR = 2; //加载失败
    private int STATE_SUCCESS = 3; //加载成功
    private int STATE_EMPTY = 4; //空

    private int current_state = STATE_LOADING; // 当前状态默认设置为 加载状态



    public LoadingPager(Context context) {
        super(context);
        this.context = context ;
        init(); //初始化 加载布局
    }


    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context ;
        init();//初始化 加载布局
    }


    private void init() {//加载布局

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            

        if(loadingView == null) {  // 正在加载的布局
            loadingView = View.inflate(context , R.layout.page_loading , null);
            this.addView(loadingView);  //this代表当前类 帧布局的子类
        }
        if(errorView == null) {  // 出现错误的布局
            errorView = View.inflate(context ,R.layout.page_error , null);
            this.addView(errorView);
        }
        if(emptyView == null) {  // q请求数据是空的布局
            emptyView = View.inflate(context,R.layout.page_empty , null);
            this.addView(emptyView);
        }

        showSafeView();//展示布局

    }

    private void showSafeView() {//展示view保证在主线程

        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showView();
            }
        });

    }

    private void showView() {
        //是否展示错误页面
        errorView.setVisibility(current_state == STATE_ERROR?View.VISIBLE : View.INVISIBLE);

        //是否展示加载界面
        loadingView.setVisibility(current_state == STATE_LOADING?View.VISIBLE : View.INVISIBLE);

        //是否展示空页面
        emptyView.setVisibility(current_state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);

        if(sucessView == null) {
            sucessView = View.inflate(context ,getViewId() , null);
            this.addView(sucessView , params);
        }

        //是否展示成功页面
        sucessView.setVisibility(current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }



    public void loadData(){   //根据不同的网络状态加载相应的页面

        AsyncHttpClient httpClient = new AsyncHttpClient();//加载数据 联网请求

        String url = getUrl();

        if(TextUtils.isEmpty(url)) {
            resultState = resultState.SUCCESS ;
            loadImage();
            return;
        }

        httpClient.post(url , new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if(TextUtils.isEmpty(content)) { // 如果请求成功  数据为空
                    resultState = resultState.EMPTY ;
                    resultState.setJson("");
                }else{  // 如果请求成功  数据不为空
                    resultState = resultState.SUCCESS ;
                    resultState.setJson(content);
                }

                loadImage();   // 根据网络连接状态 给当前状态赋值

            }

            @Override    //   当请求数据失败时
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                resultState = ResultState.ERROR;  // 枚举类
                resultState.setJson(content);
                loadImage();
                
                
            }
        });
    }

    private void loadImage() {   // 根据网络连接状态 给当前状态赋值

        switch (resultState){
            case ERROR:
                current_state = STATE_ERROR; //根据枚举值来赋值相应的状态
                break;
            case EMPTY:
                current_state = STATE_EMPTY; //根据枚举值来赋值相应的状态
                break;
            case SUCCESS:
                current_state = STATE_SUCCESS; //根据枚举值来赋值相应的状态
                break;
        }

        showSafeView();  //  状态发生改变 重新展示布局

        if (current_state == STATE_SUCCESS){

            onSuccess(resultState,sucessView); //把数据传过去
        }
    }

    protected abstract String getUrl();

    public abstract int getViewId() ;

    protected abstract void onSuccess(ResultState resultState, View sucessView);

    public enum ResultState{

        ERROR(2),SUCCESS(3),EMPTY(4); //相当于是三个ResultState对象

        private int state;
        private String json;

        ResultState(int state){  //ResultState构造器
            this.state = state;
        }


        public void setJson(String json){
            this.json = json;
        }

        public String getJson(){
            return json;
        }

    }



}
