package com.auto.mobile.moudle.home.view.dynamic.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseFragment;
import com.auto.mobile.moudle.adapter.FactoryAdapter;
import com.auto.mobile.moudle.model.Factory;
import com.auto.mobile.moudle.system.ReleaseActivity;
import com.auto.mobile.moudle.system.ReleaseDataActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//动态
public class TrendsFragment extends BaseFragment {

    TextView tvNotice;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    Unbinder unbinder;

    AutoRelativeLayout btnCommit;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    //列表适配器
    FactoryAdapter adapter;
    List<Factory> list;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.trends_layout, null);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        initView();
        return layout;
    }

    //初始化数据
    private void initData() {
        list = new ArrayList<>();
        Factory factory = new Factory();
        for (int i = 0; i < 10; i++) {
            factory.setNickName("赵丽颖" + i);
            list.add(factory);
        }
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //添加头布局
        View headView = getLayoutInflater().inflate(R.layout.trend_head, recyclerView, false);
        //头布局点击时间
        btnCommit=headView.findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReleaseDataActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.addHeaderView(headView);
        adapter = new FactoryAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        //第一次自动刷新
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.autoRefresh();
    }

    //单利模式
    public static TrendsFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        TrendsFragment fragment = new TrendsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
