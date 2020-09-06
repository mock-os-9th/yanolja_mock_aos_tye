package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.room_info.RoomInfoActivity;
import com.example.yanolkka.src.common.objects.Accommodation;
import com.example.yanolkka.src.common.objects.Motel;
import com.example.yanolkka.src.common.objects.Room;

import java.text.DecimalFormat;
import java.util.List;

public class RoomView extends LinearLayout {
    private Room room;

    private Context mContext;


    public RoomView(Context context, Room room) {
        super(context);
        this.mContext = context;
        this.room = room;

        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.item_room, this, false);

        
    }
}
