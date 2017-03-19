package com.mashaoting.guigujinrong.invest.fragment;

import android.view.View;
import android.widget.TextView;

import com.mashaoting.guigujinrong.R;
import com.mashaoting.guigujinrong.base.BaseFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/14.
 */

public class InvestHotFragment extends BaseFragment {
    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    protected void initData(String json) {
        idFlowlayout.setAdapter(new TagAdapter<String>(datas)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = new TextView(getActivity());
                tv.setText(s);
                tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.hot_shope));
                return tv;
            }
        });
    }

    @Override
    protected int getLayoutid() {
        return R.layout.hot_fragment;
    }

    @Override
    protected String getChildUrl() {
        return null;
    }


}
