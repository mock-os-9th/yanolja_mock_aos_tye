package com.example.yanolkka.src.common.views;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.yanolkka.R;

public class TermView extends LinearLayout{
    private Context mContext;

    private boolean checked = false, mandatory;
    private String termName, termDescription, termDetails;

    private LinearLayout llDetails;
    private CheckBox cbTerm;
    private TextView tvName, tvDescription, tvMandatory, tvDetails;
    private ImageView ivBtnDrop;

    public EventListener checkListener;

    public void setEventListener(EventListener l){checkListener = l;}

    public interface EventListener{
        void onCheckChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public TermView(Context context, String termName, String termDescription, boolean mandatory, String details){
        super(context);

        this.mContext = context;
        this.termName = termName;
        this.termDescription = termDescription;
        this.mandatory = mandatory;
        this.termDetails = details;

        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_term, this, true);

        llDetails = findViewById(R.id.ll_term_details);
        cbTerm = findViewById(R.id.cb_term);
        tvName = findViewById(R.id.tv_term_name);
        tvDescription = findViewById(R.id.tv_term_description);
        tvMandatory = findViewById(R.id.tv_term_mandatory);
        tvDetails = findViewById(R.id.tv_term_long_details);
        ivBtnDrop = findViewById(R.id.iv_btn_term_drop);

        cbTerm.setChecked(checked);

        cbTerm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checked = ((CheckBox)view).isChecked();
                checkListener.onCheckChanged();
            }
        });

        tvName.setText(termName);

        if (termDescription != null && !termDescription.isEmpty()){
            tvDescription.setVisibility(VISIBLE);
            tvDescription.setText(termDescription);
        }

        if (mandatory){
            tvMandatory.setTextColor(mContext.getColor(R.color.colorAccent));
            tvMandatory.setText(mContext.getString(R.string.mandatory));
        }else{
            tvMandatory.setText(mContext.getString(R.string.choice));
        }

        if (termDetails != null && !termDetails.isEmpty()){
            tvDetails.setText(termDetails);
            ivBtnDrop.setVisibility(VISIBLE);
            ivBtnDrop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (llDetails.getVisibility() == GONE){
                        llDetails.setVisibility(VISIBLE);
                        ivBtnDrop.setImageDrawable(mContext.getDrawable(R.drawable.ic_baseline_arrow_drop_up));
                    }else if(llDetails.getVisibility() == VISIBLE){
                        llDetails.setVisibility(GONE);
                        ivBtnDrop.setImageDrawable(mContext.getDrawable(R.drawable.ic_baseline_arrow_drop_down));
                    }
                }
            });
        }
    }

    public void setChecked(boolean checked){
        cbTerm.setChecked(checked);
        this.checked = checked;
    }

    public boolean getChecked(){
        return checked;
    }

    public boolean getMandatory(){
        return mandatory;
    }
}
