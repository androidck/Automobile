package com.auto.mobile.moudle.home.view.person;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.auto.mobile.R;
import com.auto.mobile.common.application.MyApplication;
import com.auto.mobile.common.base.BaseFragment;

/**
 * 个人中心
 */
public class PersonFragment extends BaseFragment {

    @Override
    protected View onCreateView() {
        FrameLayout layout= (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.person_layout,null);
        return layout;
    }
}
