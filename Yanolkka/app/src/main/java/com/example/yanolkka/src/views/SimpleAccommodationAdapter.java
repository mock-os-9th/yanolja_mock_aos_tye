package com.example.yanolkka.src.views;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.objects.Accommodation;

import java.text.DecimalFormat;
import java.util.List;

public class SimpleAccommodationAdapter extends RecyclerView.Adapter<SimpleAccommodationAdapter.MyViewHolder> {
    private List<Accommodation> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv_accommodation;
        public TextView tv_name, tv_rating, tv_reviews, tv_discount, tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_accommodation = itemView.findViewById(R.id.iv_item_simple_accommodation);
            tv_name = itemView.findViewById(R.id.tv_item_simple_accommodation_name);
            tv_rating = itemView.findViewById(R.id.tv_item_simple_accommodation_rating);
            tv_reviews = itemView.findViewById(R.id.tv_item_simple_accommodation_reviews);
            tv_discount = itemView.findViewById(R.id.tv_item_simple_accommodation_discount);
            tv_price = itemView.findViewById(R.id.tv_item_simple_accommodation_price);
        }
    }

    public SimpleAccommodationAdapter(List<Accommodation> dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_simple_accomodation, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Accommodation accommodation = dataSet.get(position);

        if (accommodation.getImageUri() != null){
            holder.iv_accommodation.setImageURI(Uri.parse(accommodation.getImageUri()));
        }

        holder.tv_name.setText(accommodation.getName());
        holder.tv_rating.setText(accommodation.getRating()+"");
        holder.tv_reviews.setText(accommodation.getReviews()+"");
        if (accommodation.getDiscount() != 0)
            holder.tv_discount.setText((Math.round(accommodation.getDiscount()*100))+"");

        DecimalFormat format = new DecimalFormat("###,###");
        holder.tv_price.setText(format.format(Math.round(accommodation.getOriginalPrice() * (1-accommodation.getDiscount()))));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
