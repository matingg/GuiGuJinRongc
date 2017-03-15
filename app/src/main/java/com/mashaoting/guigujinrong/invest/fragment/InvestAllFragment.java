package com.mashaoting.guigujinrong.invest.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.base.BaseFragment;
import com.mashaoting.guigujinrong.command.AppNetConfig;
import com.mashaoting.guigujinrong.invest.adapter.TTTAdapter;
import com.mashaoting.guigujinrong.invest.bean.InvestBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @InjectView(R.id.lv_Invest)
    ListView lvInvest;
    private InvestBean investBean;
    private List<InvestBean.DataBean> data;
    //    InvestListViewPagerAdapter adapter;

    @Override
    protected void initData(String json) {
        investBean = JSON.parseObject(json, InvestBean.class);

//         adapter = new InvestListViewPagerAdapter(context , investBean.getData());
        data = investBean.getData();
//        TextAdapter adapter = new TextAdapter(data);
//        Text3Adapter adapter = new Text3Adapter(context ,data);
//        DiSanCengAdapter adapter = new DiSanCengAdapter(context ,investBean.getData()) {
        TTTAdapter adapter = new TTTAdapter(context, investBean.getData());
        lvInvest.setAdapter(adapter);

    }

    @Override
    protected int getLayoutid() {
        return R.layout.all_fragment;
    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
