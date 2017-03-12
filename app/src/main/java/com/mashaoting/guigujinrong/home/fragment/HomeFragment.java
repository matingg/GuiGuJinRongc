package com.mashaoting.guigujinrong.home.fragment;

import android.util.Log;
import android.view.View;

import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.base.BaseFragment;
import com.mashaoting.guigujinrong.command.AppNetConfig;
import com.mashaoting.guigujinrong.utils.LoadNet;
import com.mashaoting.guigujinrong.utils.LoadNetHttp;

/**
 * Created by 麻少亭 on 2017/3/10.
 *
 *                   首页
 */

public class HomeFragment extends BaseFragment {


    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home,null);

        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "HomeFragment initData()"+"首页");

        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.i("http", "success: "+context);
            }

            @Override
            public void failure(String error) {
                Log.i("http", "failure: "+error);
            }
        });
    }
}
