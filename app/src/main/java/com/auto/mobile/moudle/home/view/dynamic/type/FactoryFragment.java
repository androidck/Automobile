package com.auto.mobile.moudle.home.view.dynamic.type;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.base.BaseFragment;
import com.auto.mobile.moudle.adapter.factory.Factory;
import com.auto.mobile.moudle.adapter.factory.FactorysAdapter;
import com.auto.mobile.moudle.adapter.factory.GroupEntry;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 汽修厂
 */
public class FactoryFragment extends BaseFragment {

    @BindView(R.id.factoryName)
    TextView factoryName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.btn_call)
    ImageButton btnCall;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    Unbinder unbinder;
    private FactorysAdapter adapter;

    ArrayList<GroupEntry> groupEntries;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.factory_layout, null);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        initView();
        return layout;
    }

    //初始化数据
    private void initData(){
        //头数据
        groupEntries=new ArrayList<>();

       /* ArrayList<Factory> list1=new ArrayList<>();
        list1.add(new Factory("精洗"));

        ArrayList<Factory> list2=new ArrayList<>();
        list2.add(new Factory("内室清洗"));
        list2.add(new Factory("打蜡"));
        list2.add(new Factory("打蜡"));
        list2.add(new Factory("打蜡"));
        list2.add(new Factory("打蜡"));
        list2.add(new Factory("打蜡"));



        groupEntries.add(new GroupEntry("洗车服务",list1));
        groupEntries.add(new GroupEntry("美容服务",list2));*/
        ArrayList<Factory> list3=new ArrayList<>();
        list3.add(new Factory("四轮定位"));
        groupEntries.add(new GroupEntry("轮胎服务",list3));
        adapter=new FactorysAdapter(getActivity(),groupEntries);
        GroupedGridLayoutManager gridLayoutManager = new GroupedGridLayoutManager(getActivity(), 4, adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    //初始化控件
    private void initView(){

    }



    //单利模式
    public static FactoryFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FactoryFragment fragment = new FactoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
