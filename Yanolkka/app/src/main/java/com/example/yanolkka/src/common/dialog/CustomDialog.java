package com.example.yanolkka.src.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yanolkka.R;

public class CustomDialog extends Dialog {
    private Context mContext;
    private String comment;
    private CustomDialogClickListener listener;
    private TextView tvComment, tvBtnPositive, tvBtnNegative;

    public CustomDialog(Context context, String comment, CustomDialogClickListener listener){
        super(context);
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        tvComment = findViewById(R.id.tv_dialog_comment);
        tvBtnPositive = findViewById(R.id.tv_btn_dialog_positive);
        tvBtnNegative = findViewById(R.id.tv_btn_dialog_negative);

        tvComment.setText(comment);

        tvBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPositiveClick();
                dismiss();
            }
        });

        tvBtnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNegativeClick();
                dismiss();
            }
        });
    }
}
