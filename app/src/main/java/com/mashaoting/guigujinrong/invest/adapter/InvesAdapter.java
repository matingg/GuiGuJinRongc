package com.mashaoting.guigujinrong.invest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mashaoting.guigujinrong.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/14.
 */

public class InvesAdapter extends FragmentPagerAdapter {

    private List<BaseFragment>datas = new ArrayList<>();

    public InvesAdapter(FragmentManager fm, List<BaseFragment> datas) {
        super(fm);

        if(datas != null) {
            this.datas.clear();
            this.datas.addAll(datas);
        }

    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
