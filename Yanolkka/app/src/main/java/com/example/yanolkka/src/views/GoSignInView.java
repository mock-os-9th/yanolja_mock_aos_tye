package com.example.yanolkka.src.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.sign_up.SignUpActivity;
import com.example.yanolkka.src.activities.sign_in.SignInActivity;

public class GoSignInView extends LinearLayout implements View.OnClickListener {

    private Context mContext;

    public GoSignInView(Context context) {
        super(context);
        init(context);
    }

    public GoSignInView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GoSignInView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.view_go_sign_in, this, true);

        findViewById(R.id.rl_btn_go_sign_in).setOnClickListener(this);
        findViewById(R.id.rl_btn_go_sign_up).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_btn_go_sign_in:
                goActivity(SignInActivity.class);
                break;
            case R.id.rl_btn_go_sign_up:
                goActivity(SignUpActivity.class);
                break;
        }
    }

    private void goActivity(Class c){
        Intent intent = new Intent(mContext, c);
        mContext.startActivity(intent);
    }
}
