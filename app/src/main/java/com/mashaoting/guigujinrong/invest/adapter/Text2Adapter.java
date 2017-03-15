package com.mashaoting.guigujinrong.invest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/14.
 */

public abstract class Text2Adapter<T> extends BaseAdapter {


    private  List<T> datas;
    private  Context context;

    public Text2Adapter(Context context, List<T> data) {
        this.context = context;
        if(data != null && data.size()>0) {
            datas = data;
        }

    }

    @Override
    public int getCount() {
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
            convertView = initVIew();
            viewHolder = new ViewHolder(initVIew());

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
             T t = datas.get(position);
             setData(t ,convertView);
        return convertView;
    }

    protected abstract void setData(T t ,View view);

    public abstract View initVIew();

    static class ViewHolder {

        ViewHolder(View view) {
            view.setTag(this);

        }
    }
}
