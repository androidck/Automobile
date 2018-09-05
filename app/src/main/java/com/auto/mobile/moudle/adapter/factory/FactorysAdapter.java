package com.auto.mobile.moudle.adapter.factory;

import android.content.Context;

import com.auto.mobile.R;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;

import java.util.ArrayList;

/**
 * 分组服务适配器
 */
public class FactorysAdapter extends GroupedRecyclerViewAdapter {

    private ArrayList<GroupEntry> mGroups;

    private Context mContext;



    public FactorysAdapter(Context context) {
        super(context);
    }

    public FactorysAdapter(Context context, ArrayList<GroupEntry> arrayList) {
        super(context);
        this.mContext=context;
        this.mGroups=arrayList;
    }

    @Override
    public int getGroupCount() {
        return mGroups==null ? 0 : mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Factory> children = mGroups.get(groupPosition).getFactories();
        return children==null ? 0: children.size();

    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_service_head;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_service_context;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        final GroupEntry entity=mGroups.get(groupPosition);
        holder.setText(R.id.tv_service_headName,entity.getHead());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        Factory factory=mGroups.get(childPosition).getFactories().get(childPosition);
        holder.setText(R.id.tv_service_name,factory.getFactoryName());
    }
}
