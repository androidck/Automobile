package com.auto.mobile.moudle.system;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.auto.mobile.moudle.adapter.GetServiceAdapter;
import com.auto.mobile.moudle.model.GetService;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    List<GetService> list;
    GetServiceAdapter adapter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


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
        initData();
        initView();
    }

    private void initTopBar() {
        topBar.setTitle("获得的服务");
        topBar.addLeftImageButton(R.mipmap.fanhui, R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化数据
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GetService getService = new GetService();
            getService.setServiceName("服务" + i);
            getService.setServiceDate(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
            getService.setServiceDetails("服务：更换机油");
            list.add(getService);
        }

    }

    private void initView() {
        refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        adapter = new GetServiceAdapter(GetServiceActivity.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(GetServiceActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        refreshLayout.setEnableFooterFollowWhenLoadFinished(true);

        //第一次进入演示刷新
        refreshLayout.autoRefresh();
    }

}

