package com.mashaoting.guigujinrong.invest.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.base.BaseFragment;
import com.mashaoting.guigujinrong.invest.adapter.InvesAdapter;
import com.mashaoting.guigujinrong.invest.bean.InvestBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 麻少亭 on 2017/3/10.
 * <p>
 * 我要投资
 */

public class InvestFragment extends BaseFragment {


    @InjectView(R.id.tv_fragment_1)
    TextView tvFragment1;
    @InjectView(R.id.tv_fragment_2)
    TextView tvFragment2;
    @InjectView(R.id.tv_fragment_3)
    TextView tvFragment3;
    @InjectView(R.id.vp_invest)
    ViewPager vpInvest;
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;

    private List<BaseFragment> fragmentList;
    private List<InvestBean.DataBean> datas;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    protected void initData(String json) {

        datas = JSON.parseObject(json, InvestBean.class).getData();

        baseTitle.setText("投资");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
        initFragment();
        initViewPager();//初始化viewpager

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new InvestAllFragment());
        fragmentList.add(new InvestRecommendFragment());
        fragmentList.add(new InvestHotFragment());


    }

    private void initViewPager() {

        InvesAdapter adapter = new InvesAdapter(getFragmentManager(), fragmentList);
        vpInvest.setAdapter(adapter);// 给viewpager 设置适配器
        vpInvest.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        selectText(0);//设置默认选择第一个 位置选择在监听事件以后
//        vpInvest.setCurrentItem(0); 不显示第一个 二三个显示
    }

    private void selectText(int position) {
        hiddenTextViewState();
        switch (position) {
            case 0:
                tvFragment1.setBackgroundResource(R.color.colorAccent);

                break;
            case 1:
                tvFragment2.setBackgroundResource(R.color.colorPrimaryDark);

                break;
            case 2:
                tvFragment3.setBackgroundResource(R.color.colorPrimary);

                break;
        }
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest;
    }

    @Override
    protected String getChildUrl() {


        return null;
    }


    private void hiddenTextViewState() {
        tvFragment1.setBackgroundColor(Color.WHITE);
        tvFragment2.setBackgroundColor(Color.WHITE);
        tvFragment3.setBackgroundColor(Color.WHITE);
    }

    @OnClick({R.id.tv_fragment_1, R.id.tv_fragment_2, R.id.tv_fragment_3})
    public void onClick(View view) {
        hiddenTextViewState();

        switch (view.getId()) {
            case R.id.tv_fragment_1:
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_fragment_2:
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_fragment_3:
                vpInvest.setCurrentItem(2);
                break;
        }
    }


}
