package com.mashaoting.guigujinrong;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mashaoting.guigujinrong.base.BaseFragment;
import com.mashaoting.guigujinrong.home.fragment.HomeFragment;
import com.mashaoting.guigujinrong.invest.fragment.InvestFragment;
import com.mashaoting.guigujinrong.more.fragment.MoreFragment;
import com.mashaoting.guigujinrong.property.fragment.PropertyFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.main_fl)
    FrameLayout mainFl;
    @InjectView(R.id.main_rg)
    RadioGroup mainRg;

    List<BaseFragment> fragmentList;
    private  int currentCheched; //当前被选中的 RadioGroup的那个
    private BaseFragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();   //初始化Fragment

        initListener();   //初始化 RadioGroup 监听事件

        Log.e("TAG", "MainActivity onCreate()"+getPackageName()+"---"+getAssets());

        mainRg.check(R.id.mai_rb_home); //默认选择哪个Fragment 写在  initListener();   后面
    }

    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mai_rb_home:
                        currentCheched = 0 ;
                        break;
                    case R.id.mai_rb_invest:
                        currentCheched = 1 ;
                        break;
                    case R.id.mai_rb_property:
                        currentCheched = 2 ;
                        break;
                    case R.id.mai_rb_more:
                        currentCheched = 3 ;
                        break;
                }

                switchFragment (fragmentList.get(currentCheched));
            }
        });

    }

    private void switchFragment(BaseFragment currentFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();//开启事物
        if(tempFragment != currentFragment) {  // 如果 当 前的 和 缓存 的不是同一个

            if(!currentFragment.isAdded()) {     //如果当前的没有添加过
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.main_fl , currentFragment);
            }else {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
        }

            ft.commit();
        tempFragment = currentFragment;
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new PropertyFragment());
        fragmentList.add(new MoreFragment());

    }

        private boolean isDouble = false ;
    @Override    //              双 击 退 出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(MainActivity.this, "再按一下试试", Toast.LENGTH_SHORT).show();
            if(isDouble) {
                finish();
            }
            isDouble = true ;

            new Timer().schedule(new TimerTask() {  //  双击退出 如果 超过两秒 更改 boolean 状态
                @Override
                public void run() {
                    isDouble = false ;
                }
            } , 2000);

        }
        return false;
    }
}
