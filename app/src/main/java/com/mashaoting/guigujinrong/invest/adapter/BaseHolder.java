package com.mashaoting.guigujinrong.invest.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 麻少亭 on 2017/3/15.
 */
public abstract class BaseHolder<T> {

    protected abstract void setChildData();
    public abstract View initView();
    private T t;
    private final View rootView;

    public T getData() {
        return t;
    }

    public void setData(T t) {
        this.t = t;
        setChildData();
    }


    public View getView() {
        return rootView;
    }
    public BaseHolder() {
        rootView = initView();
        ButterKnife.inject(this, rootView);
        rootView.setTag(this);
    }


}
