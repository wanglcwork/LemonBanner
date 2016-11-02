package com.lemon.pear.banner.moducle.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lemon.pear.banner.R;
import com.lemon.pear.banner.moducle.main.common.OnRecyclerItemClickListener;

import java.util.List;

/**
 * Created by ning on 2016/11/2.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MyViewHolder> {

    private Context context;

    private List<String> mDatas;

    private int layoutRes;

    private OnRecyclerItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnRecyclerItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MainListAdapter(Context context, List<String> mDatas, int layoutRes) {
        this.context = context;
        this.mDatas = mDatas;
        this.layoutRes = layoutRes;
    }

    @Override
    public int getItemViewType(int position) {
        //是用来根据position的不同来实现RecyclerView中对不同布局的要求
        return super.getItemViewType(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder.itemView, holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tvItem);
        }
    }

}
