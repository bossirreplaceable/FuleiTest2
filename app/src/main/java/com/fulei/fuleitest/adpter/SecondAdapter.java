package com.fulei.fuleitest.adpter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
 * Created by gcl on 2017/8/1.
 */

public class SecondAdapter extends MultiChoiceAdapter<SecondAdapter.MViewHolder> {

    private List<GoodsWay> goodsList;
    private MViewHolder viewHolder;
    private Context mContext;
   // private OnRecyclerViewItemClickListener itemClickListener = null;
    private List<Integer> selectedIndices;


    public SecondAdapter(Context c, List<GoodsWay> list) {
        this.mContext = c;
        this.goodsList = list;
        //  selectedIndices = new ArrayList<Integer>();
        Log.e("yobo----------------------",3+"");

    }

//    public interface OnRecyclerViewItemClickListener {
//
//        void onItemClick(View view, int postion);
//
//    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new MViewHolder(LayoutInflater.from
                (mContext).inflate(R.layout.item_second, parent,false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        GoodsWay goods = goodsList.get(position);
        holder.item.setText(goods.get_id() + "");
        holder.item_x.setText("X:" + goods.getX());
        holder.item_y.setText("Y:" + goods.getY());
        holder.item_z.setText("Z:" + goods.getZ());

//        if (selectedIndices.contains(position)) {
//            holder.item_right.setBackgroundColor(mContext.getResources().getColor(R.color.lightseagreen));
//            holder.item.setBackgroundColor(mContext.getResources().getColor(R.color.lightseagreen));
//        } else {
//            holder.item_right.setBackgroundColor(mContext.getResources().getColor(R.color.lightgray));
//            holder.item.setBackgroundColor(mContext.getResources().getColor(R.color.lightgray));
//        }


    }

    @Override
    public void setActive(@NonNull View view, boolean state) {
        super.setActive(view, state);
        Toast.makeText(mContext, "Click on item------------ ", Toast.LENGTH_SHORT).show();
         LinearLayout item_right=view.findViewById(R.id.item_right);
        if (item_right!=null) {
            if (state) {
                item_right.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));

            } else {
                item_right.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
            }
        }

    }

    @Override
    protected View.OnClickListener defaultItemViewClickListener(MViewHolder holder, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("yobo----------------------",7+"");
                Toast.makeText(mContext, "Click on item " + position, Toast.LENGTH_SHORT).show();
//                if (itemClickListener != null) {
//                    itemClickListener.onItemClick(view, position);
//                }
            }
        };
    }

    //    public void setSelectedIndices(final boolean isAll) {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (isAll) {
//                    for (int i = 0; i < 100; i++) {
//                        selectedIndices.add(i);
//                    }
//                } else {
//                    selectedIndices.clear();
//                }
//                Message msg=new Message();
//                msg.what=2;
//                handler.sendMessage(msg);
//            }
//        }).start();
//    }
//
//    public List<Integer> getSelectedIndices() {
//
//        return selectedIndices;
//
//    }
//
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what){
//                case 1:
//                    notifyItemChanged((int)msg.obj);
//                    break;
//                case 2:
//                    notifyDataSetChanged();
//                    break;
//
//                default:
//                    break;
//
//            }
//
//        }
//    };
//
//    public void toggleSelected(final int index) {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (selectedIndices.contains(index)) {
//                    selectedIndices.remove((Integer) index);
//                } else {
//                    selectedIndices.add(index);
//                }
//                Message msg = new Message();
//                msg.what = 1;
//                msg.obj=index;
//                handler.sendMessage(msg);
//
//            }
//        }).start();
// //       notifyItemChanged(index);
////        if (itemClickListener != null) {
////            itemClickListener.onSelectionChanged(selectedIndices.size());
////        }
//
//    }

    @Override
    public int getItemCount() {

        return goodsList.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder{

        private TextView item;
        private TextView item_x;
        private TextView item_y;
        private TextView item_z;
        private LinearLayout item_right;

        public MViewHolder(View itemView) {
            super(itemView);
            Log.e("yobo----------------------",8+"");
            item_right = itemView.findViewById(R.id.item_right);
            item = itemView.findViewById(R.id.second_item);
            item_x = itemView.findViewById(R.id.item_x);
            item_y = itemView.findViewById(R.id.item_y);
            item_z = itemView.findViewById(R.id.item_z);

        }

//        @Override
//        public void onClick(View view) {
//            if (itemClickListener != null) {
//                itemClickListener.onItemClick(view, getAdapterPosition());
//            }
//
//        }
    }
}
