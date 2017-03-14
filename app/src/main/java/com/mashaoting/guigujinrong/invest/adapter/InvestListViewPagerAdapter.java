package com.mashaoting.guigujinrong.invest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.invest.bean.InvestBean;
import com.mashaoting.guigujinrong.ui.MyProgress;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.mashaoting.guigujinrong.R.id.p_minzouzi;

/**
 * Created by 麻少亭 on 2017/3/14.
 */

public class InvestListViewPagerAdapter extends BaseAdapter {


    private  List<InvestBean.DataBean> datas;
    private  Context context;

    public InvestListViewPagerAdapter(Context context, List<InvestBean.DataBean> data) {
        this.context = context;
        if(data != null && data.size()>0) {
            datas = data;
        }

    }

    @Override
    public int getCount() {
        Log.e("TAG", "InvestListViewPagerAdapter getCount()"+ datas.size()+"2225252525");
        return datas.size();

    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_invest, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.pName .setText( datas.get(position).getName());
        viewHolder.pMinnum.setText(datas.get(position).getMemberNum());
        viewHolder.pYearlv.setText(datas.get(position).getYearRate());
        viewHolder.pSuodingdays.setText(datas.get(position).getSuodingDays());
        viewHolder.pMoney.setText(datas.get(position).getMinTouMoney());
        viewHolder.pProgresss.setProgress(Integer.parseInt(datas.get(position).getProgress()));


        return convertView;
    }

    static class ViewHolder {
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
        @InjectView(p_minzouzi)
        TextView pMinzouzi;
        @InjectView(R.id.p_minnum)
        TextView pMinnum;
        @InjectView(R.id.p_progresss)
        MyProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
