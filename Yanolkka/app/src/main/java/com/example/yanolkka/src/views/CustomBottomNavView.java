package com.example.yanolkka.src.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanolkka.R;

public class CustomBottomNavView extends LinearLayout implements View.OnClickListener {

    public EventListener listener;

    public void setEventListener(EventListener l){listener = l;}

    public interface EventListener{
        void turnPage(int i);
    }

    private int itemId = R.id.ll_nav_home;

    public CustomBottomNavView(Context context) {
        super(context);
        init(context);
    }

    public CustomBottomNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomBottomNavView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_custom_bottom_nav, this, true);

        findViewById(R.id.ll_nav_home).setOnClickListener(this);
        findViewById(R.id.ll_nav_location).setOnClickListener(this);
        findViewById(R.id.ll_nav_nearby).setOnClickListener(this);
        findViewById(R.id.ll_nav_like).setOnClickListener(this);
        findViewById(R.id.ll_nav_my_page).setOnClickListener(this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {
        int chosenId = view.getId();

        if (itemId != chosenId){
            clearAccents();
            ImageView ivChosen;
            TextView tvChosen;
            int accentColor = getResources().getColor(R.color.textNormal);
            switch (chosenId){
                case R.id.ll_nav_home:
                    ivChosen = (ImageView)findViewById(R.id.iv_nav_home);
                    ivChosen.setImageDrawable(getResources().getDrawable(R.drawable.tab_home_accent));
                    tvChosen = (TextView)findViewById(R.id.tv_nav_home);
                    tvChosen.setTextColor(accentColor);
                    listener.turnPage(0);
                    break;
                case R.id.ll_nav_location:
                    ivChosen = (ImageView)findViewById(R.id.iv_nav_location);
                    ivChosen.setImageDrawable(getResources().getDrawable(R.drawable.tab_location_accent));
                    tvChosen = (TextView)findViewById(R.id.tv_nav_location);
                    tvChosen.setTextColor(accentColor);
                    listener.turnPage(1);
                    break;
                case R.id.ll_nav_nearby:
                    ivChosen = (ImageView)findViewById(R.id.iv_nav_nearby);
                    ivChosen.setImageDrawable(getResources().getDrawable(R.drawable.tab_nearby_accent));
                    tvChosen = (TextView)findViewById(R.id.tv_nav_nearby);
                    tvChosen.setTextColor(accentColor);
                    listener.turnPage(2);
                    break;
                case R.id.ll_nav_like:
                    ivChosen = (ImageView)findViewById(R.id.iv_nav_like);
                    ivChosen.setImageDrawable(getResources().getDrawable(R.drawable.tab_like_accent));
                    tvChosen = (TextView)findViewById(R.id.tv_nav_like);
                    tvChosen.setTextColor(accentColor);
                    listener.turnPage(3);
                    break;
                case R.id.ll_nav_my_page:
                    ivChosen = (ImageView)findViewById(R.id.iv_nav_my_page);
                    ivChosen.setImageDrawable(getResources().getDrawable(R.drawable.tab_my_page_accent));
                    tvChosen = (TextView)findViewById(R.id.tv_nav_my_page);
                    tvChosen.setTextColor(accentColor);
                    listener.turnPage(4);
                    break;
            }
            invalidate();
            itemId = chosenId;
        }
    }

    private void clearAccents(){
        Resources resources = getResources();
        ((ImageView)findViewById(R.id.iv_nav_home)).setImageDrawable(resources.getDrawable(R.drawable.tab_home));
        ((ImageView)findViewById(R.id.iv_nav_location)).setImageDrawable(resources.getDrawable(R.drawable.tab_location));
        ((ImageView)findViewById(R.id.iv_nav_nearby)).setImageDrawable(resources.getDrawable(R.drawable.tab_nearby));
        ((ImageView)findViewById(R.id.iv_nav_like)).setImageDrawable(resources.getDrawable(R.drawable.tab_like));
        ((ImageView)findViewById(R.id.iv_nav_my_page)).setImageDrawable(resources.getDrawable(R.drawable.tab_my_page));

        int normalTextColor = resources.getColor(R.color.textSmooth);
        ((TextView)findViewById(R.id.tv_nav_home)).setTextColor(normalTextColor);
        ((TextView)findViewById(R.id.tv_nav_location)).setTextColor(normalTextColor);
        ((TextView)findViewById(R.id.tv_nav_nearby)).setTextColor(normalTextColor);
        ((TextView)findViewById(R.id.tv_nav_like)).setTextColor(normalTextColor);
        ((TextView)findViewById(R.id.tv_nav_my_page)).setTextColor(normalTextColor);

        invalidate();
    }

    public void onBackKey(){
        clearAccents();
        ImageView ivOnBack = (ImageView)findViewById(R.id.iv_nav_home);
        ivOnBack.setImageDrawable(getResources().getDrawable(R.drawable.tab_home_accent));
        TextView tvOnBack = (TextView)findViewById(R.id.tv_nav_home);
        tvOnBack.setTextColor(getResources().getColor(R.color.textNormal));
        listener.turnPage(0);
        itemId = R.id.ll_nav_home;
    }
}
