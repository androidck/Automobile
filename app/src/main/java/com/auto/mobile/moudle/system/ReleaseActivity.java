package com.auto.mobile.moudle.system;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的发布
 */
public class ReleaseActivity extends BaseActivity {
    @BindView(R.id.topBar)
    QMUITopBar topBar;
    @BindView(R.id.ed_context)
    EditText edContext;
    @BindView(R.id.btn_commit)
    QMUIRoundButton btnCommit;

    @Override
    protected int getContextViewId() {
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_layout);
        setStatusBarLightMode(ReleaseActivity.this);
        ButterKnife.bind(this);
        initTopBar();
    }

    //初始化返回键
    private void initTopBar() {
        topBar.setTitle("提交信息");
        topBar.addLeftImageButton(R.mipmap.fanhui,R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
