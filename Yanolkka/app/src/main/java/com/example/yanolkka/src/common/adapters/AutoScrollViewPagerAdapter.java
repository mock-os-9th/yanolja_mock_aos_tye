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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AutoScrollViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Integer> imageList;

    public AutoScrollViewPagerAdapter(Context context, ArrayList<Integer> imageList){
        this.mContext = context;
        this.imageList = imageList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_auto_scroll_view_pager,null);
        ImageView ivContainer = (ImageView) v.findViewById(R.id.iv_item_auto_scroll_view_pager);
        ivContainer.setImageResource(imageList.get(position));
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
