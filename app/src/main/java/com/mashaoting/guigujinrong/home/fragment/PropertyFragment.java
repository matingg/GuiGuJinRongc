package com.mashaoting.guigujinrong.home.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.guigujinrong.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/3/10.
 *
 *                   我的资产
 */

public class PropertyFragment extends BaseFragment {

    TextView textView ;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(45);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    @Override
    public void initData() {
        Log.e("TAG", "HomeFragment initData()"+"我的资产");
        textView.setText("我的资产");

    }
}
