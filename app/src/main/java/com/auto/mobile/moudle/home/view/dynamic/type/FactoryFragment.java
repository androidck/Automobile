package com.auto.mobile.moudle.home.view.dynamic.type;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseFragment;

/**
 * 汽修厂
 */
public class FactoryFragment extends BaseFragment {
    @Override
    protected View onCreateView() {
        FrameLayout layout= (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.factory_layout,null);
        return layout;
    }

    //单利模式
    public static FactoryFragment newInstance(int type){
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        FactoryFragment fragment=new FactoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
