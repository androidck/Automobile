package com.auto.mobile.moudle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.moudle.model.GetService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 获得的服务 adapter
 */
public class GetServiceAdapter extends RecyclerView.Adapter<GetServiceViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private List<GetService> mDatas;

    public GetServiceAdapter(Context context, List<GetService> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GetServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        GetServiceViewHolder holder = new GetServiceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GetServiceViewHolder holder, int position) {
        GetService getService=mDatas.get(position);
        holder.bindData(getService);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class GetServiceViewHolder extends RecyclerView.ViewHolder {

    ImageView item_img;
    TextView item_name;
    TextView item_date;
    TextView tv_service;

    public GetServiceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        item_img=itemView.findViewById(R.id.item_img);
        item_name=itemView.findViewById(R.id.item_name);
        item_date=itemView.findViewById(R.id.item_date);
        tv_service=itemView.findViewById(R.id.tv_service);
    }

    //绑定数据
    public void bindData(GetService getService){
        item_name.setText(getService.getServiceName());
        item_date.setText(getService.getServiceDate());
        tv_service.setText(getService.getServiceDetails());
    }
}
