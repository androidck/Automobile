package com.auto.mobile.moudle.system;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseActivity;
import com.auto.mobile.moudle.adapter.FactoryAdapter;
import com.auto.mobile.moudle.adapter.MyReleaseAdapter;
import com.auto.mobile.moudle.model.Factory;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的发布
 */
public class ReleaseActivity extends BaseActivity {
    @BindView(R.id.topBar)
    QMUITopBar topBar;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    List<Factory> list;
    MyReleaseAdapter adapter;

    @Override
    protected int getContextViewId() {
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_release_layout);
        setStatusBarLightMode(ReleaseActivity.this);
        ButterKnife.bind(this);
        initData();
        initTopBar();
        initView();
    }

    //初始化数据
    private void initData(){
        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Factory factory = new Factory();
            factory.setNickName("赵丽颖" + i);
            list.add(factory);
        }
    }

    //初始化返回键
    private void initTopBar() {
        topBar.setTitle("我的发布"+"("+list.size()+")");
        topBar.addLeftImageButton(R.mipmap.fanhui, R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(ReleaseActivity.this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyReleaseAdapter(ReleaseActivity.this, list);
        recyclerView.setAdapter(adapter);
        //第一次自动刷新
        refreshLayout.setRefreshHeader(new ClassicsHeader(ReleaseActivity.this));
        refreshLayout.autoRefresh();
    }


}
