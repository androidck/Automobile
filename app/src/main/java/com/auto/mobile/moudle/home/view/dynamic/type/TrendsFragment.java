package com.auto.mobile.moudle.home.view.dynamic.type;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseFragment;
import com.auto.mobile.moudle.system.ReleaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//动态
public class TrendsFragment extends BaseFragment {
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.btn_commit)
    AutoRelativeLayout btnCommit;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.trends_layout, null);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
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

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        Intent intent=new Intent(getActivity(), ReleaseActivity.class);
        startActivity(intent);
    }
}
