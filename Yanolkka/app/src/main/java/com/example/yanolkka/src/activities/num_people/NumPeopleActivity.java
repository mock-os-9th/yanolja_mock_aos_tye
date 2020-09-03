package com.example.yanolkka.src.activities.num_people;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.activities.BaseActivity;
import com.example.yanolkka.src.common.views.KidView;

public class NumPeopleActivity extends BaseActivity implements View.OnClickListener {

    private int numAdult, numKid;

    private LinearLayout llKid, llKidViews;
    private TextView tvNumAdult, tvNumKid;
    private ImageView ivBtnMinusAdult, ivBtnPlusAdult, ivBtnMinusKid, ivBtnPlusKid;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_people);

        Intent intent = getIntent();
        numAdult = intent.getIntExtra("numAdult", 2);
        numKid = intent.getIntExtra("numKid", 0);

        setViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setViews(){

        llKid = findViewById(R.id.ll_num_people_kids);
        llKidViews = findViewById(R.id.ll_kid_view);

        tvNumAdult = findViewById(R.id.tv_num_adults);
        tvNumAdult.setText(numAdult+"");
        tvNumKid = findViewById(R.id.tv_num_kids);
        tvNumKid.setText(numKid+"");

        ivBtnMinusAdult = findViewById(R.id.iv_btn_adult_minus);
        ivBtnPlusAdult = findViewById(R.id.iv_btn_adult_plus);
        ivBtnMinusKid = findViewById(R.id.iv_btn_kid_minus);
        ivBtnPlusKid = findViewById(R.id.iv_btn_kid_plus);

        if (numAdult <= 0){
            ivBtnMinusAdult.setEnabled(false);
            ivBtnMinusAdult.setImageDrawable(getDrawable(R.drawable.btn_minus_disabled));
        }else{
            ivBtnMinusAdult.setEnabled(true);
            ivBtnMinusAdult.setImageDrawable(getDrawable(R.drawable.btn_minus));
        }

        if (numKid <= 0){
            ivBtnMinusKid.setEnabled(false);
            ivBtnMinusKid.setImageDrawable(getDrawable(R.drawable.btn_minus_disabled));

            llKidViews.removeAllViews();
            llKid.setVisibility(View.GONE);
        }else{
            ivBtnMinusKid.setEnabled(true);
            ivBtnMinusKid.setImageDrawable(getDrawable(R.drawable.btn_minus));

            llKid.setVisibility(View.VISIBLE);
            for (int i = 1; i <= numKid; i++) {
                llKidViews.addView(new KidView(this, i));
            }
        }

        if (numKid >= 10){
            ivBtnPlusKid.setEnabled(false);
            ivBtnPlusKid.setImageDrawable(getDrawable(R.drawable.btn_plus_disabled));
        }

        ivBtnMinusAdult.setOnClickListener(this);
        ivBtnPlusAdult.setOnClickListener(this);
        ivBtnMinusKid.setOnClickListener(this);
        ivBtnPlusKid.setOnClickListener(this);

        findViewById(R.id.iv_num_people_back).setOnClickListener(this);
        findViewById(R.id.rl_btn_num_people_apply).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_num_people_back:
                finish();
                break;

            case R.id.rl_btn_num_people_apply:
                numAdult = Integer.parseInt(tvNumAdult.getText().toString());
                numKid = Integer.parseInt(tvNumKid.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("numAdult", numAdult);
                intent.putExtra("numKid", numKid);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.iv_btn_adult_minus:
                if (numAdult > 0){
                    ivBtnMinusAdult.setEnabled(true);
                    ivBtnMinusAdult.setImageDrawable(getDrawable(R.drawable.btn_minus));
                    numAdult--;
                    tvNumAdult.setText(numAdult+"");
                }
                if (numAdult <= 0){
                    ivBtnMinusAdult.setEnabled(false);
                    ivBtnMinusAdult.setImageDrawable(getDrawable(R.drawable.btn_minus_disabled));
                }
                break;

            case R.id.iv_btn_adult_plus:
                ivBtnMinusAdult.setEnabled(true);
                ivBtnMinusAdult.setImageDrawable(getDrawable(R.drawable.btn_minus));
                numAdult++;
                tvNumAdult.setText(numAdult+"");
                break;

            case R.id.iv_btn_kid_minus:
                if (numKid > 0){
                    ivBtnMinusKid.setEnabled(true);
                    ivBtnMinusKid.setImageDrawable(getDrawable(R.drawable.btn_minus));
                    numKid--;
                    tvNumKid.setText(numKid+"");
                    llKidViews.removeView(llKidViews.getChildAt(numKid));
                }
                if (numKid <= 0){
                    ivBtnMinusKid.setEnabled(false);
                    ivBtnMinusKid.setImageDrawable(getDrawable(R.drawable.btn_minus_disabled));
                    llKid.setVisibility(View.GONE);
                }
                break;

            case R.id.iv_btn_kid_plus:
                if (numKid <= 0){
                    ivBtnMinusKid.setEnabled(true);
                    ivBtnMinusKid.setImageDrawable(getDrawable(R.drawable.btn_minus));
                }
                if (numKid < 10){
                    ivBtnPlusKid.setEnabled(true);
                    ivBtnPlusKid.setImageDrawable(getDrawable(R.drawable.btn_plus));
                    llKid.setVisibility(View.VISIBLE);
                    numKid++;
                    tvNumKid.setText(numKid+"");
                    llKidViews.addView(new KidView(this, numKid));
                }else{
                    ivBtnPlusKid.setEnabled(false);
                    ivBtnMinusKid.setImageDrawable(getDrawable(R.drawable.btn_plus_disabled));
                }
                break;
        }
    }
}