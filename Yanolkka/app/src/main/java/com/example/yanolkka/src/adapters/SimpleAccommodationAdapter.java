package com.example.yanolkka.src.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseActivity;
import com.example.yanolkka.src.objects.Accommodation;

import java.text.DecimalFormat;
import java.util.List;

public class SimpleAccommodationAdapter extends RecyclerView.Adapter<SimpleAccommodationAdapter.MyViewHolder> {
    private List<Accommodation> dataSet;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivAccommodation;
        public TextView tvName, tvRating, tvReviews, tvDiscount, tvPrice;

        public MyViewHolder(final Context context, @NonNull View itemView) {
            super(itemView);
            ivAccommodation = itemView.findViewById(R.id.iv_item_simple_accommodation);
            tvName = itemView.findViewById(R.id.tv_item_simple_accommodation_name);
            tvRating = itemView.findViewById(R.id.tv_item_simple_accommodation_rating);
            tvReviews = itemView.findViewById(R.id.tv_item_simple_accommodation_reviews);
            tvDiscount = itemView.findViewById(R.id.tv_item_simple_accommodation_discount);
            tvPrice = itemView.findViewById(R.id.tv_item_simple_accommodation_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //미개발(숙소 상세 페이지 이동 추가 예정)
                    ((BaseActivity)context).goYetActivity();
                }
            });
        }
    }

    public SimpleAccommodationAdapter(Context context, List<Accommodation> dataSet){
        this.mContext = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_simple_accomodation, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(mContext, v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Accommodation accommodation = dataSet.get(position);

        if (accommodation.getImageUri() != null){
            holder.ivAccommodation.setImageURI(Uri.parse(accommodation.getImageUri()));
        }

        holder.tvName.setText(accommodation.getName());
        holder.tvRating.setText(accommodation.getRating()+"");
        holder.tvReviews.setText(accommodation.getReviews()+"");
        if (accommodation.getDiscount() != 0)
            holder.tvDiscount.setText((Math.round(accommodation.getDiscount()*100))+"");

        DecimalFormat format = new DecimalFormat("###,###");
        holder.tvPrice.setText(format.format(Math.round(accommodation.getOriginalPrice() * (1-accommodation.getDiscount()))));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
