package com.example.yanolkka.src.common.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.yanolkka.src.common.yet.YetFragment;
import com.example.yanolkka.src.activities.search_result.fragments.ResultHotelFragment;
import com.example.yanolkka.src.activities.search_result.fragments.ResultMotelFragment;

import java.util.List;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;
    private List<String> titles;

    public ContentsPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.mPageCount = titles.size();
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ResultMotelFragment.newInstance();

            case 1:
                return ResultHotelFragment.newInstance();

//            case 2:
//                return ResultPensionFragment.newInstance();

            default:
                return YetFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
