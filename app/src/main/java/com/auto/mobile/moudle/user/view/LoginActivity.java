package com.auto.mobile.moudle.user.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.qmuiteam.qmui.alpha.QMUIAlphaButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户登录
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.ed_phone)
    EditText edPhone;//手机号
    @BindView(R.id.ed_pwd)
    EditText edPwd;//密码
    @BindView(R.id.btn_login)
    QMUIAlphaButton btnLogin;//登录
    @BindView(R.id.tv_forgot)
    TextView tvForgot;//忘记密码
    @BindView(R.id.tv_register)
    TextView tvRegister;//快速注册

    @Override
    protected int getContextViewId() {
        return R.id.login_activity_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);
        ButterKnife.bind(this);
        setStatusBarLightMode(LoginActivity.this);
    }

    @OnClick({R.id.btn_login, R.id.tv_forgot, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
            case R.id.tv_forgot:
                break;
            case R.id.tv_register:
                break;
        }
    }
}
