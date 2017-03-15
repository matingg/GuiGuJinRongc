package com.mashaoting.guigujinrong.invest.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/15.
 */

public class TTTAdapter extends DiSanCengAdapter {
    public TTTAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public BaseHolder getHolder() {
        return new TTTViewHolder();
    }
}
