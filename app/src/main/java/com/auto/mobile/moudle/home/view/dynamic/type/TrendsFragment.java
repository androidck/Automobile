package com.auto.mobile.moudle.home.view.dynamic.type;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseFragment;

//动态
public class TrendsFragment extends BaseFragment {
    @Override
    protected View onCreateView() {
        FrameLayout layout= (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.trends_layout,null);
        return layout;
    }

    //单利模式
    public static TrendsFragment newInstance(int type){
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        TrendsFragment fragment=new TrendsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
