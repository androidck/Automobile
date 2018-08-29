package com.auto.mobile;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.FrameLayout;

import com.auto.mobile.common.base.BaseActivity;
import com.auto.mobile.common.view.BottomBar;
import com.auto.mobile.moudle.home.view.dynamic.DynamicFragment;
import com.auto.mobile.moudle.home.view.person.PersonFragment;
import com.auto.mobile.moudle.user.view.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    //底部导航栏
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    protected int getContextViewId() {
        return R.id.mian_activity_id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarLightMode(MainActivity.this);
        ButterKnife.bind(this);
        initView();
    }

    //初始化布局
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initView(){
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#febe09")
                .addItem(DynamicFragment.class,
                        "首页",
                        R.mipmap.home_page01,
                        R.mipmap.home_page)
                .addItem(PersonFragment.class,
                        "我的",
                        R.mipmap.home_my,
                        R.mipmap.home_my01)
                .build();
    }
}
