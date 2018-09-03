package com.auto.mobile.moudle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.auto.mobile.R;
import com.auto.mobile.common.view.RoundImageView;
import com.auto.mobile.moudle.model.Factory;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.List;

/**
 * 我的发布适配器
 */
public class MyReleaseAdapter extends RecyclerView.Adapter<MyReleaseViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    private List<Factory> mData;

    public MyReleaseAdapter(Context context, List<Factory> mData){
        this.mContext=context;
        inflater=LayoutInflater.from(context);
        this.mData=mData;
    }


    @NonNull
    @Override
    public MyReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_trends, parent, false);
        MyReleaseViewHolder viewHolder=new MyReleaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyReleaseViewHolder holder, int position) {
        //绑定数据
        Factory factory=mData.get(position);
        holder.bindData(factory);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class MyReleaseViewHolder extends RecyclerView.ViewHolder{

    RoundImageView headImg;
    TextView tv_NickName,tv_time,tv_distance,tv_details,tv_address;
    QMUIRoundButton btn_read;
    ImageButton ig_btn_phone;

    public MyReleaseViewHolder(View itemView) {
        super(itemView);
        headImg=itemView.findViewById(R.id.item_image);
        tv_NickName=itemView.findViewById(R.id.tv_NickName);
        tv_time=itemView.findViewById(R.id.tv_time);
        tv_distance=itemView.findViewById(R.id.tv_distance);
        tv_details=itemView.findViewById(R.id.tv_details);
        tv_address=itemView.findViewById(R.id.tv_address);
        btn_read=itemView.findViewById(R.id.btn_read);
        ig_btn_phone=itemView.findViewById(R.id.ig_btn_phone);
    }

    public void bindData(Factory factory){
        tv_NickName.setText(factory.getNickName());//设置昵称
    }
}
