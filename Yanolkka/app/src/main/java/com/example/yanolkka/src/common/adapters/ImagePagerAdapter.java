package com.example.yanolkka.src.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.yanolkka.R;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> imageList;

    public ImagePagerAdapter(Context context, ArrayList<Integer> imageList){
        this.mContext = context;
        this.imageList = imageList;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pager_image, null);

        ImageView imageView = view.findViewById(R.id.iv_pager_image);
        imageView.setImageResource(imageList.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View)object;
    }
}
