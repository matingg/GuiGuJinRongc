package com.mashaoting.guigujinrong.invest.adapter;

import android.view.View;
import android.widget.TextView;

import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.invest.bean.InvestBean;
import com.mashaoting.guigujinrong.ui.MyProgress;
import com.mashaoting.guigujinrong.utils.UIUtils;

import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/15.
 */

public class TTTViewHolder extends BaseHolder<InvestBean.DataBean> {
    @InjectView(R.id.p_name)
    TextView pName;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.p_money)
    TextView pMoney;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @InjectView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @InjectView(R.id.p_minnum)
    TextView pMinnum;
    @InjectView(R.id.p_progresss)
    MyProgress pProgresss;
    private InvestBean.DataBean data;


    @Override
    protected void setChildData() {
        data = getData();
        pName.setText(data.getName());
    }

    @Override
    public View initView() {
        return UIUtils.getView(R.layout.item_invest);
    }
}
