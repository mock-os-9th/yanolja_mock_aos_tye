package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.yanolkka.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReviewView extends LinearLayout {
    private Context mContext;

    private LinearLayout llReply;
    private RatingBar rbReview;
    private TextView tvUserName, tvReservationOption, tvCreated, tvReview,
            tvReply, tvReplyCreated;

    private String userName, roomName, reserveOption, content, reply, created, replyCreated;
    private float rating;

    private SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat toFormat = new SimpleDateFormat("yyyy.MM.dd");

    public ReviewView(Context context, String userName, String roomName, String reserveOption, String content,
                      String reply, String created, String replyCreated, float rating) throws ParseException {
        super(context);
        this.mContext = context;
        this.userName = userName;
        this.roomName = roomName;
        this.reserveOption = reserveOption;
        this.content = content;
        this.reply = reply;
        this.created = created;
        this.replyCreated = replyCreated;
        this.rating = rating;
        init();
    }

    private void init() throws ParseException {
        LayoutInflater.from(mContext).inflate(R.layout.view_review, this, true);
        llReply = findViewById(R.id.ll_review_reply);
        rbReview = findViewById(R.id.rb_review);
        tvUserName = findViewById(R.id.tv_review_nickname);
        tvReservationOption = findViewById(R.id.tv_review_reservation_info);
        tvCreated = findViewById(R.id.tv_review_created);
        tvReview = findViewById(R.id.tv_review);
        tvReply = findViewById(R.id.tv_review_reply);
        tvReplyCreated = findViewById(R.id.tv_review_reply_created);

        if (created.length() > 10){
            created = toFormat.format(fromFormat.parse(created));
        }
        if (reply != null && replyCreated.length() > 10){
            replyCreated = toFormat.format(fromFormat.parse(replyCreated));
        }

        if (reply != null){
            llReply.setVisibility(VISIBLE);
            tvReply.setText(reply);
            tvReplyCreated.setText(replyCreated);
        }else
            llReply.setVisibility(GONE);

        tvUserName.setText(userName);
        String type = reserveOption.equals("P") ? mContext.getString(R.string.motelRental) : mContext.getString(R.string.stayNight);
        tvReservationOption.setText(roomName+"-"+type);
        tvReview.setText(content);
        tvCreated.setText(created);

        rbReview.setRating(rating);
    }
}
