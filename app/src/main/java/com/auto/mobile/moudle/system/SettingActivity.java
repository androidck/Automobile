package com.auto.mobile.moudle.system;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系统设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.topBar)
    QMUITopBar topBar;
    @BindView(R.id.btn_loginOut)
    QMUIRoundButton btnLoginOut;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.updatPwd)
    LinearLayout updatPwd;

    @Override
    protected int getContextViewId() {
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        ButterKnife.bind(this);
        setStatusBarLightMode(SettingActivity.this);
        initTopBar();
        initView();
    }

    private void initTopBar() {
        topBar.setTitle("设置");
        topBar.addLeftImageButton(R.mipmap.fanhui, R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化控件
    private void initView() {

    }


}
