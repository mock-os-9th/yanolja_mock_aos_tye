package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanolkka.R;

public class NoticeView extends LinearLayout{
    private Context mContext;
    private String notice;
    private TextView tvNotice;

    public NoticeView(Context context, String notice){
        super(context);
        this.mContext = context;
        this.notice = notice;
        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_notice, this, true);

        tvNotice = findViewById(R.id.tv_view_notice);
        tvNotice.setText(notice);
    }
}
