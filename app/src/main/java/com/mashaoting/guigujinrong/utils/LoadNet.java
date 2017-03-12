package com.mashaoting.guigujinrong.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by 麻少亭 on 2017/3/12.
 */

public class LoadNet {




    public static void getDataPost(String url ,final LoadNetHttp http){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.post(url , new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (http != null) {
                    http.success(content);
                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (http != null) {
                    http.success(content);
                }
            }
        });
    }
}
