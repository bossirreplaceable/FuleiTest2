package com.fulei.fuleitest.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter;
import com.fulei.fuleitest.R;
import com.fulei.fuleitest.greendao.GoodsWay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoBo on 2017/8/9.
 */



public class SecondAdapter1  extends MultiChoiceAdapter<SecondAdapter1.MVHolder>{

    private List<GoodsWay> mList;
    private Context mContext;
    private OnRecyclerViewItemClickListener itemClickListener = null;
    public SecondAdapter1(List<GoodsWay> list,Context c) {
        this.mList=list;
        this.mContext=c;
    }
    @Override
    public MVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MVHolder(LayoutInflater.from(mContext).inflate(R.layout.item_second,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(MVHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.item_id.setText(position+"");
        GoodsWay goods = mList.get(position);
        holder.item_id.setText(goods.get_id() + "");
        holder.item_x.setText("X:" + goods.getX());
        holder.item_y.setText("Y:" + goods.getY());
        holder.item_z.setText("Z:" + goods.getZ());
    }

    @Override
    public void setActive(@NonNull View view, boolean state) {

        LinearLayout item_right=view.findViewById(R.id.item_right);
        TextView  item_id=view.findViewById(R.id.second_item);
        if (item_right!=null){
            if (state){
                item_right.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightseagreen));
                item_id.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightseagreen));
            }else {
                item_right.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
                item_id.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
            }
        }
    }

        public interface OnRecyclerViewItemClickListener {

        void onItemClick(View view, int postion);

    }
    public void setItemListener(OnRecyclerViewItemClickListener listener){
        this.itemClickListener=listener;
    }

    @Override
    protected View.OnClickListener defaultItemViewClickListener(MVHolder holder, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener!=null){
                    itemClickListener.onItemClick(view,position);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MVHolder extends  RecyclerView.ViewHolder{
        TextView item_id;
        TextView item_x;
        TextView item_y;
        TextView item_z;

        public MVHolder(View view){
            super(view);
            item_id=view.findViewById(R.id.second_item);
            item_x = itemView.findViewById(R.id.item_x);
            item_y = itemView.findViewById(R.id.item_y);
            item_z = itemView.findViewById(R.id.item_z);


        }

    }









}
