package com.auto.mobile.moudle.home.view.person;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.application.MyApplication;
import com.auto.mobile.common.base.BaseFragment;
import com.auto.mobile.common.view.RoundImageView;
import com.auto.mobile.moudle.system.GetServiceActivity;
import com.auto.mobile.moudle.system.ReleaseActivity;
import com.auto.mobile.moudle.system.SettingActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心
 */
public class PersonFragment extends BaseFragment {

    @BindView(R.id.topBar)
    QMUITopBar topBar;
    @BindView(R.id.img_head)
    RoundImageView imgHead;
    @BindView(R.id.tv_NickName)
    TextView tvNickName;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_setup)
    TextView tvSetup;
    Unbinder unbinder;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.person_layout, null);
        unbinder = ButterKnife.bind(this, layout);
        initTopBar();
        return layout;
    }

    private void initTopBar(){
        topBar.setTitle("我的");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //点击事件
    @OnClick({R.id.img_head, R.id.tv_release, R.id.tv_service, R.id.tv_setup})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_head:
                break;
            case R.id.tv_release:
                intent=new Intent(getActivity(), ReleaseActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_service:
                intent=new Intent(getActivity(), GetServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_setup:
                intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
