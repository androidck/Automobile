package com.auto.mobile.moudle.system;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 获得的服务
 */
public class GetServiceActivity extends BaseActivity {

    @BindView(R.id.topBar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getContextViewId() {
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        setStatusBarLightMode(GetServiceActivity.this);
        ButterKnife.bind(this);
        initTopBar();
    }

    private void initTopBar(){
        topBar.setTitle("获得的服务");
        topBar.addLeftImageButton(R.mipmap.fanhui,R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
