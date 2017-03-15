package com.mashaoting.guigujinrong.invest.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.invest.bean.InvestBean;
import com.mashaoting.guigujinrong.utils.UIUtils;

import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/15.
 */

public class Text3Adapter extends Text2Adapter<InvestBean.DataBean> {


    private final List<InvestBean.DataBean> data;

    public Text3Adapter(Context context, List<InvestBean.DataBean> data) {
        super(context, data);

        this.data = data;
    }

    @Override
    protected void setData(InvestBean.DataBean dataBean, View view) {
           TextView pname = (TextView) view.findViewById(R.id.p_name);
        pname.setText(dataBean.getName());
    }

    @Override
    public View initVIew() {
        return UIUtils.getView(R.layout.item_invest);
    }


}
