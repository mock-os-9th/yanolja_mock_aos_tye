package com.example.yanolkka.src.common.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.room_info.RoomInfoActivity;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.objects.Accommodation;
import com.example.yanolkka.src.common.objects.Motel;

import java.text.DecimalFormat;
import java.util.List;

public class ExpandedAccommodationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Accommodation> dataSet;

    private Context mContext;

    public interface ItemClickListener{
        void onItemClick(View v, int pos);
    }

    private ItemClickListener mListener = null;

    public void setItemClickListener(ItemClickListener listener){
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout llRental, llNight;
        public ImageView ivAccommodation;
        public RatingBar rbRating;
        public TextView tvName, tvRating, tvReviews, tvDiscount, tvOriginalPrice, tvPrice, tvStars, tvLocation
                , tvRentalDiscount, tvRentalOriginalPrice, tvRentalPrice, tvCheckIn, tvRentalLength, tvIdx, tvType,
                tvRentalCurrency, tvNightCurrency;

        public MyViewHolder(final Context context, @NonNull View itemView) {
            super(itemView);
            ivAccommodation = itemView.findViewById(R.id.iv_item_expanded_accommodation);
            llRental = itemView.findViewById(R.id.ll_item_expanded_motel);
            llNight = itemView.findViewById(R.id.ll_item_expanded_hotel);
            rbRating = itemView.findViewById(R.id.rb_item_expanded_accommodation);
            tvName = itemView.findViewById(R.id.tv_item_expanded_accommodation_name);
            tvRating = itemView.findViewById(R.id.tv_item_expanded_accommodation_rating);
            tvReviews = itemView.findViewById(R.id.tv_item_expanded_accommodation_reviews);
            tvDiscount = itemView.findViewById(R.id.tv_item_expanded_accommodation_night_discount);
            tvOriginalPrice = itemView.findViewById(R.id.tv_item_expanded_accommodation_night_original_price);
            tvPrice = itemView.findViewById(R.id.tv_item_expanded_accommodation_night_price);
            tvRentalDiscount = itemView.findViewById(R.id.tv_item_expanded_accommodation_rental_discount);
            tvRentalOriginalPrice = itemView.findViewById(R.id.tv_item_expanded_accommodation_rental_original_price);
            tvRentalPrice = itemView.findViewById(R.id.tv_item_expanded_accommodation_rental_price);
            tvCheckIn = itemView.findViewById(R.id.tv_item_expanded_accommodation_check_in);
            tvRentalLength = itemView.findViewById(R.id.tv_item_expanded_accommodation_rental_length);
            tvStars = itemView.findViewById(R.id.tv_item_expanded_hotel_stars);
            tvIdx = itemView.findViewById(R.id.tv_item_expanded_accommodation_idx);
            tvType = itemView.findViewById(R.id.tv_item_expanded_accommodation_type);
            tvLocation = itemView.findViewById(R.id.tv_item_expanded_accommodation_location);
            tvRentalCurrency = itemView.findViewById(R.id.tv_item_expanded_accommodation_rental_price_currency);
            tvNightCurrency = itemView.findViewById(R.id.tv_item_expanded_accommodation_night_price_currency);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        mListener.onItemClick(view, pos);
                    }
                }
            });
        }
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDataSize;
        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDataSize = itemView.findViewById(R.id.tv_result_count);
        }
    }

    public ExpandedAccommodationAdapter(Context context, List<Accommodation> dataSet){
        this.mContext = context;
        this.dataSet = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else if(position > 20) return 3;
        else return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        if (viewType == 1){
            v = LayoutInflater.from(mContext)
                    .inflate(R.layout.result_size, parent, false);
            return new TopViewHolder(v);
        }else{
            v = viewType == 2 ? LayoutInflater.from(mContext)
                    .inflate(R.layout.item_expanded_accomodation, parent, false) :
                    LayoutInflater.from(mContext).inflate(R.layout.item_expanded_accomodation_below, parent, false);
            return new MyViewHolder(mContext, v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Accommodation accommodation = dataSet.get(position);

        if (holder instanceof TopViewHolder){
            ((TopViewHolder) holder).tvDataSize.setText(dataSet.size()-1+"");
        }else{
            MyViewHolder mvHolder = (MyViewHolder) holder;

            DecimalFormat format = new DecimalFormat("###,###");

            if (accommodation.getImageUri() != null){
                mvHolder.ivAccommodation.setImageURI(Uri.parse(accommodation.getImageUri()));
            }

            mvHolder.tvIdx.setText(accommodation.getIdx()+"");
            mvHolder.tvType.setText(accommodation.getType()+"");

            mvHolder.tvName.setText(accommodation.getName());
            mvHolder.rbRating.setRating(accommodation.getRating());
            mvHolder.tvRating.setText(String.format("%1.1f", accommodation.getRating()));
            mvHolder.tvReviews.setText("("+format.format(accommodation.getReviews())+")");

            if (accommodation.getType() == 'h')
                mvHolder.tvStars.setText(accommodation.getStars()+"성급");

            if (accommodation instanceof Motel){
                mvHolder.llRental.setVisibility(View.VISIBLE);

                Motel motel = (Motel) accommodation;

                if (motel.getImageUri() == null){
                    mvHolder.ivAccommodation.setImageDrawable(mContext.getDrawable(R.drawable.motel_sample));
                }

                if (!motel.isPartTimeAvailable()){
                    mvHolder.tvRentalPrice.setText(mContext.getString(R.string.reserveFull));
                    mvHolder.tvRentalLength.setVisibility(View.GONE);
                    mvHolder.tvRentalCurrency.setVisibility(View.GONE);
                }else{
                    if (accommodation.getDiscount() != 0.0f){
                        mvHolder.tvRentalDiscount.setText((Math.round(motel.getDiscountRental()*100))+"%~");
                        mvHolder.tvRentalOriginalPrice.setText(format.format(motel.getOriginalRentalPrice())+"원");
                        mvHolder.tvRentalOriginalPrice.setPaintFlags(mvHolder.tvRentalOriginalPrice.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        mvHolder.tvRentalDiscount.setVisibility(View.VISIBLE);
                        mvHolder.tvRentalOriginalPrice.setVisibility(View.VISIBLE);
                    }else{
                        mvHolder.tvRentalDiscount.setVisibility(View.INVISIBLE);
                        mvHolder.tvRentalOriginalPrice.setVisibility(View.INVISIBLE);
                    }
                    mvHolder.tvRentalLength.setText(motel.getRentalLength()+"시간");
                    mvHolder.tvRentalPrice.setText(format.format(Math.round(motel.getOriginalRentalPrice()
                            * (1-motel.getDiscountRental()))));
                }

            }else{
                mvHolder.llRental.setVisibility(View.GONE);
            }

            if (accommodation.getGuide() != null){
                mvHolder.tvLocation.setText(accommodation.getGuide());
            }

            if (!accommodation.isAllDayAvailable()){
                mvHolder.tvPrice.setText(R.string.reserveFull);
                mvHolder.tvCheckIn.setVisibility(View.GONE);
                mvHolder.tvNightCurrency.setVisibility(View.GONE);
            }else{
                if (accommodation.getDiscount() != 0.0f){
                    mvHolder.tvDiscount.setText((Math.round(accommodation.getDiscount()*100))+"%~");
                    mvHolder.tvOriginalPrice.setText(format.format(accommodation.getOriginalPrice())+"원");
                    mvHolder.tvOriginalPrice.setPaintFlags(mvHolder.tvRentalOriginalPrice.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG);
                    mvHolder.tvDiscount.setVisibility(View.VISIBLE);
                    mvHolder.tvOriginalPrice.setVisibility(View.VISIBLE);
                }else{
                    mvHolder.tvDiscount.setVisibility(View.INVISIBLE);
                    mvHolder.tvOriginalPrice.setVisibility(View.INVISIBLE);
                }
                mvHolder.tvCheckIn.setText(String.format("%02d:%02d부터", accommodation.getHourCheckIn(), accommodation.getMinuteCheckIn()));
                mvHolder.tvPrice.setText(format.format(Math.round(accommodation.getOriginalPrice() * (1-accommodation.getDiscount()))));
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
