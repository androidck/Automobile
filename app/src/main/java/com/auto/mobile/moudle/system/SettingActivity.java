package com.auto.mobile.moudle.system;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;


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


    @OnClick({R.id.updatPwd, R.id.btn_loginOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updatPwd:
                break;
            case R.id.btn_loginOut:
                new QMUIDialog.MessageDialogBuilder(SettingActivity.this)
                        .setTitle("退出登录")
                        .setMessage("确定要退出登录吗？")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction(0, "退出", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                                final QMUITipDialog tipDialog = new QMUITipDialog.Builder(SettingActivity.this)
                                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                                        .setTipWord("账号已退出")
                                        .create();
                                tipDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tipDialog.dismiss();
                                    }
                                },1500);
                            }
                        })
                        .create(mCurrentDialogStyle).show();
                break;
        }
    }
}
