package com.mashaoting.guigujinrong.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashaoting.guigujinrong.ui.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by 麻少亭 on 2017/3/10.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;
    public Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPager = new LoadingPager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resultState, View sucessView) {
                ButterKnife.inject(BaseFragment.this, sucessView);
                initData(resultState.getJson());
                context = getActivity();
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            public int getViewId() {
                return getLayoutid();
            }
        };
        return loadingPager;
    }

    protected abstract void initData(String json);

    protected abstract int getLayoutid();

    protected abstract String getChildUrl();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadingPager.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
