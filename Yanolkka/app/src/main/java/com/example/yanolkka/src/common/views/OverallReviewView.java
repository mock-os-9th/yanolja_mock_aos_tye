package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanolkka.R;

public class OverallReviewView extends LinearLayout {
    private Context mContext;

    private TextView tvReviewCount, tvOverallRating,
            tvKindness, tvClean, tvConvenience, tvFixtures;

    private View vDefault, vKindness, vClean, vConvenience, vFixtures;

    private int reviews;
    private float overallRating, kindness, clean, convenience, fixtures;

    public OverallReviewView(Context context, int reviews, float overallRating,
                             float kindness, float clean, float convenience, float fixtures){
        super(context);
        this.mContext = context;

        this.reviews = reviews;
        this.overallRating = overallRating;
        this.kindness = kindness;
        this.clean = clean;
        this.convenience = convenience;
        this.fixtures = fixtures;

        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_overall_review, this, true);

        tvReviewCount = findViewById(R.id.tv_overall_review_count);
        tvOverallRating = findViewById(R.id.tv_overall_review_rating);
        tvClean = findViewById(R.id.tv_overall_review_clean);
        tvKindness = findViewById(R.id.tv_overall_review_kindness);
        tvConvenience = findViewById(R.id.tv_overall_review_convenience);
        tvFixtures = findViewById(R.id.tv_overall_review_fixtures);

        vDefault = findViewById(R.id.view_overall_review_default);
        vKindness = findViewById(R.id.view_overall_review_kindness);
        vClean = findViewById(R.id.view_overall_review_clean);
        vConvenience = findViewById(R.id.view_overall_review_convenience);
        vFixtures = findViewById(R.id.view_overall_review_fixtures);

        tvReviewCount.setText(reviews+"");
        tvOverallRating.setText(String.format("%1.1f", overallRating));
        tvKindness.setText(String.format("%1.1f", kindness));
        tvClean.setText(String.format("%1.1f", clean));
        tvConvenience.setText(String.format("%1.1f", convenience));
        tvFixtures.setText(String.format("%1.1f", fixtures));

        FrameLayout.LayoutParams paramsK = new FrameLayout.LayoutParams((int)(dpToPx(80) * (kindness/5.f)), LayoutParams.MATCH_PARENT);
        vKindness.setLayoutParams(paramsK);

        FrameLayout.LayoutParams paramsCl = new FrameLayout.LayoutParams((int)(dpToPx(80) * (clean/5.f)), LayoutParams.MATCH_PARENT);
        vClean.setLayoutParams(paramsCl);

        FrameLayout.LayoutParams paramsCo = new FrameLayout.LayoutParams((int)(dpToPx(80) * (convenience/5.f)), LayoutParams.MATCH_PARENT);
        vConvenience.setLayoutParams(paramsCo);

        FrameLayout.LayoutParams paramsF = new FrameLayout.LayoutParams((int)(dpToPx(80) * (fixtures/5.f)), LayoutParams.MATCH_PARENT);
        vFixtures.setLayoutParams(paramsF);

        invalidate();
    }

    private int dpToPx(float dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
        return px;
    }
}
