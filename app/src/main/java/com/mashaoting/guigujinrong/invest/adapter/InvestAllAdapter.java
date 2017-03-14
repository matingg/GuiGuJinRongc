package com.mashaoting.guigujinrong.invest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mashaoting.guigujinrong.invest.bean.InvestBean;

import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/14.
 */

public class InvestAllAdapter extends FragmentPagerAdapter {


    private  List<InvestBean.DataBean> datas;


    public InvestAllAdapter(FragmentManager fm, List<InvestBean.DataBean> data) {
        super(fm);
        datas = data ;
    }


    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
