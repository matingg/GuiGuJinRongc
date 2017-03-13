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

    private int current_state = STATE_LOADING;



    public LoadingPager(Context context) {
        super(context);
        this.context = context ;
        init();
    }


    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context ;
        init();
    }


    private void init() {
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            
        //加载布局
        if(loadingView == null) {
            loadingView = View.inflate(context , R.layout.page_loading , null);
            this.addView(loadingView);
        }
        if(errorView == null) {
            errorView = View.inflate(context ,R.layout.page_error , null);
            this.addView(errorView);
        }
        if(emptyView == null) {
            emptyView = View.inflate(context,R.layout.page_empty , null);
            this.addView(emptyView);
        }
        //展示布局
        showSafeView();

    }

    private void showSafeView() {
        //展示view保证在主线程
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


    //根据不同的网络状态加载相应的页面
    public void loadData(){
        //加载wangl
        AsyncHttpClient httpClient = new AsyncHttpClient();

        String url = getUrl();

        httpClient.post(url , new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if(TextUtils.isEmpty(content)) {
                    resultState = resultState.EMPTY ;
                    resultState.setJson("");
                }else{
                    resultState = resultState.SUCCESS ;
                    resultState.setJson(content);
                }

                loadImage();

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                resultState = ResultState.ERROR;
                resultState.setJson(content);
                loadImage();
                
                
            }
        });
    }

    private void loadImage() {
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

        showSafeView();
        if (current_state == STATE_SUCCESS){
            //把数据传过去
            onSuccess(resultState,sucessView);
        }
    }

    protected abstract void onSuccess(ResultState resultState, View sucessView);
    public enum ResultState{
        //相当于是三个ResultState对象
        ERROR(2),SUCCESS(3),EMPTY(4);
        private int state;
        ResultState(int state){
            this.state = state;
        }

        private String json;
        public void setJson(String json){
            this.json = json;
        }

        public String getJson(){
            return json;
        }

    }

    protected abstract String getUrl();

    public abstract int getViewId() ;


}
