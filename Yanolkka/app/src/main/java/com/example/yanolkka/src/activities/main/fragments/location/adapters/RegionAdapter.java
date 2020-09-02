package com.example.yanolkka.src.activities.main.fragments.location.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanolkka.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionAdapter extends BaseAdapter {

    private List<String> cities;

    public RegionAdapter(String[] arr){
        this.cities = new ArrayList<>(Arrays.asList(arr));
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_region_city, viewGroup, false);
        }

        TextView tvRegion = view.findViewById(R.id.tv_item_region_city);
        tvRegion.setText(cities.get(i));

        return view;
    }
}
