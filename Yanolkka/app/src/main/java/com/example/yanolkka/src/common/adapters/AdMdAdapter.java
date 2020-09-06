package com.example.yanolkka.src.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import java.util.ArrayList;

public class AdMdAdapter extends RecyclerView.Adapter<AdMdAdapter.MyViewHolder> {
    private ArrayList<Integer> dataSet;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivItem;

        public MyViewHolder(final Context context, @NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.iv_item_md);
        }
    }

    public AdMdAdapter(Context context, ArrayList<Integer> dataSet){
        this.mContext = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_md, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(mContext, v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivItem.setImageResource(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
