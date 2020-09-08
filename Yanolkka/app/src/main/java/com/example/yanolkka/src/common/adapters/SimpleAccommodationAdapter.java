package com.example.yanolkka.src.common.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.room_info.RoomInfoActivity;
import com.example.yanolkka.src.common.objects.Accommodation;

import java.text.DecimalFormat;
import java.util.List;

public class SimpleAccommodationAdapter extends RecyclerView.Adapter<SimpleAccommodationAdapter.MyViewHolder> {
    private List<Accommodation> dataSet;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivAccommodation;
        public TextView tvName, tvRating, tvReviews, tvDiscount, tvPrice, tvIdx;

        public MyViewHolder(final Context context, @NonNull View itemView) {
            super(itemView);
            ivAccommodation = itemView.findViewById(R.id.iv_item_simple_accommodation);
            tvName = itemView.findViewById(R.id.tv_item_simple_accommodation_name);
            tvRating = itemView.findViewById(R.id.tv_item_simple_accommodation_rating);
            tvReviews = itemView.findViewById(R.id.tv_item_simple_accommodation_reviews);
            tvDiscount = itemView.findViewById(R.id.tv_item_simple_accommodation_discount);
            tvPrice = itemView.findViewById(R.id.tv_item_simple_accommodation_price);
            tvIdx = itemView.findViewById(R.id.tv_item_simple_accommodation_idx);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ((BaseActivity)context).goYetActivity();

                    Intent intent = new Intent(new Intent(context, RoomInfoActivity.class));
                    intent.putExtra("accName", tvName.getText().toString());
                    intent.putExtra("rating", tvRating.getText().toString());
                    intent.putExtra("reviews", tvReviews.getText().toString());
                    intent.putExtra("idx", Integer.parseInt(tvIdx.getText().toString()));

                    context.startActivity(intent);
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

        holder.tvIdx.setText(accommodation.getIdx()+"");
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
