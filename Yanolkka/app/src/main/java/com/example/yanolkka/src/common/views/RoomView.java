package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.objects.Room;

import java.text.DecimalFormat;

public class RoomView extends LinearLayout {
    private Room room;
    private Context mContext;

    private LinearLayout llMotel;
    private TextView tvName, tvCapacity, tvRentalTime,
            tvRentalPrice, tvCheckIn, tvPrice;
    private ImageView ivRoom;

    public RoomView(Context context, Room room) {
        super(context);
        this.mContext = context;
        this.room = room;

        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.item_room, this, true);

        llMotel = findViewById(R.id.ll_item_room_motel);
        tvName = findViewById(R.id.tv_item_room_name);
        tvCapacity = findViewById(R.id.tv_item_room_capacity_standard);
        tvRentalTime = findViewById(R.id.tv_item_room_maximum_time);
        tvRentalPrice = findViewById(R.id.tv_item_room_rental_price);
        tvCheckIn = findViewById(R.id.tv_item_room_check_in_time);
        tvPrice = findViewById(R.id.tv_item_room_price);
        ivRoom = findViewById(R.id.iv_item_room);

        DecimalFormat format = new DecimalFormat("###,###");

        tvName.setText(room.getName());
        tvCapacity.setText(String.format("기준 %d명 / 최대 %d명", room.getNumStandard(), room.getNumMaximum()));

        if (room.getImageUri() == null)
            ivRoom.setImageResource(R.drawable.room_sample);
        else
            ivRoom.setImageURI(Uri.parse(room.getImageUri()));

        if (room.getType() == 'm'){
            llMotel.setVisibility(VISIBLE);
            tvRentalTime.setText(String.format("최대 %d시간", room.getRentalTime()));
            tvRentalPrice.setText(format.format(room.getRentalPrice()));
        }
        else
            llMotel.setVisibility(GONE);

        tvCheckIn.setText(String.format(room.getCheckIn()+"부터"));
        tvPrice.setText(format.format(room.getStayingPrice()));;

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //예약 화면 intent
            }
        });
    }
}
