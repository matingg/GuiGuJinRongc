package com.mashaoting.guigujinrong.more.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.guigujinrong.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/3/10.
 *
 *                   更多
 */

public class MoreFragment extends BaseFragment {

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
        Log.e("TAG", "HomeFragment initData()"+"更多");
        textView.setText("更多");

    }
}
