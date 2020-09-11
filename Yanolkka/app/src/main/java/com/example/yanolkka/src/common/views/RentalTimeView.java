package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanolkka.R;

import java.util.Calendar;

public class RentalTimeView extends LinearLayout {
    private Context mContext;
    private int hour, hourNow;
    private boolean selected, enabled;
    private TextView tvTime;

    public RentalTimeView(Context context, int hour){
        super(context);
        this.mContext = context;
        this.hour = hour;
        this.selected = false;
        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_rental_time, this, true);
        tvTime = findViewById(R.id.tv_rental_time);
        tvTime.setText(hour+":00");

        Calendar calendar = Calendar.getInstance();
        hourNow = calendar.get(Calendar.HOUR_OF_DAY);
        enabled = hour > hourNow;

        if (!enabled){
            tvTime.setBackground(getResources().getDrawable(R.drawable.button_rect_light_gray_borders));
            tvTime.setTextColor(getResources().getColor(R.color.textSmooth));
            tvTime.setEnabled(false);
        }else{
            if (selected){
                tvTime.setBackground(getResources().getDrawable(R.drawable.button_rect_accent));
                tvTime.setTextColor(getResources().getColor(R.color.colorBackground));
            }else{
                tvTime.setBackground(getResources().getDrawable(R.drawable.button_rect_black));
                tvTime.setTextColor(getResources().getColor(R.color.textNormal));
            }
        }
    }

    public void setSelected(boolean b){
        if (b){
            tvTime.setBackground(getResources().getDrawable(R.drawable.button_rect_accent));
            tvTime.setTextColor(getResources().getColor(R.color.colorBackground));
        }else{
            tvTime.setBackground(getResources().getDrawable(R.drawable.button_rect_black));
            tvTime.setTextColor(getResources().getColor(R.color.textNormal));
        }
        selected = b;
    }

    public int getHourNow(){return hourNow;}

    public boolean getSelected(){return selected;}

    public int getHour() {
        return hour;
    }

    public boolean isEnabled(){return enabled;}
}
