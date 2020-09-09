package com.example.yanolkka.src.activities.reservation;

import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.yanolkka.R;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.views.RentalTimeView;
import com.example.yanolkka.src.common.views.TermView;

import java.util.ArrayList;
import java.util.List;

public class ReservationActivity extends BaseActivity {
    private final int ON_FOOT = 1, BY_VEHICLE = 2;

    private LinearLayout llRentalTime, llReservationChange, llVisitor, llTerms;
    private TextView tvAccomName, tvRoomName, tvDateCheckIn, tvDayCheckIn,
            tvTimeCheckIn, tvDateCheckOut, tvDayCheckOut, tvTimeCheckOut,
            tvReservationPrice, tvDiscountPrice, tvFinalPrice, tvPayOption,
            tvPayOptionInfo, tvBtnOnFoot, tvBtnByVehicle, tvBtnFinishReservation;
    private EditText etReservationName, etContact, etVisitorName, etVisitorContact;
    private RadioGroup rgPayOptions;
    private CheckBox cbVisitor, cbAllAgree;

    private String[] paymentInfo, termsArr;
    private int payOption = 1, comingBy = 0;
    private boolean changeContact = false;

    private List<CheckBox> terms = new ArrayList<>();
    private List<RentalTimeView> timeViews = new ArrayList<>();
    private int rentalStart = 10, rentalEnd = 23, rentalTime = 4;

    private InputMethodManager imm;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        paymentInfo = getResources().getStringArray(R.array.paymentOptions);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        termsArr = getResources().getStringArray(R.array.reservationAgreementTerms);

        setViews();
    }

    private void setViews(){
        llRentalTime = findViewById(R.id.ll_rental_time);
        llReservationChange = findViewById(R.id.ll_reservation_change_contact);
        llVisitor = findViewById(R.id.ll_reservation_visitor);
        llTerms = findViewById(R.id.ll_reservation_terms);

        tvAccomName = findViewById(R.id.tv_reservation_accommodation_name);
        tvRoomName = findViewById(R.id.tv_reservation_room_name);
        tvDateCheckIn = findViewById(R.id.tv_reservation_check_in_date);
        tvDayCheckIn = findViewById(R.id.tv_reservation_check_in_day);
        tvTimeCheckIn = findViewById(R.id.tv_reservation_check_in_time);
        tvDateCheckOut = findViewById(R.id.tv_reservation_check_out_date);
        tvDayCheckOut = findViewById(R.id.tv_reservation_check_out_day);
        tvTimeCheckOut = findViewById(R.id.tv_reservation_check_out_time);
        tvReservationPrice = findViewById(R.id.tv_reservation_price);
        tvDiscountPrice = findViewById(R.id.tv_reservation_discount_price);
        tvFinalPrice = findViewById(R.id.tv_reservation_final_price);
        tvPayOption = findViewById(R.id.tv_reservation_pay_option);
        tvPayOptionInfo = findViewById(R.id.tv_reservation_payment_info);
        tvBtnOnFoot = findViewById(R.id.tv_btn_reservation_on_foot);
        tvBtnByVehicle = findViewById(R.id.tv_btn_reservation_by_vehicle);
        tvBtnFinishReservation = findViewById(R.id.tv_btn_reservation_pay);

        etReservationName = findViewById(R.id.et_reservation_name);
        etContact = findViewById(R.id.et_reservation_contact);
        etVisitorName = findViewById(R.id.et_reservation_visitor_name);
        etVisitorContact = findViewById(R.id.et_reservation_visitor_contact);

        rgPayOptions = findViewById(R.id.rg_reservation_payment);

        cbVisitor = findViewById(R.id.cb_reservation_visitor);
        cbAllAgree = findViewById(R.id.cb_reservation_terms_all);

        cbVisitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    llVisitor.setVisibility(View.VISIBLE);
                else
                    llVisitor.setVisibility(View.GONE);
            }
        });

        rgPayOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                tvPayOption.setText(((RadioButton)radioGroup.getChildAt(i-1)).getText());
                tvPayOptionInfo.setText(paymentInfo[i-1]);
                payOption = i-1;
            }
        });

        tvPayOption.setText(getString(R.string.kakaopay));
        tvPayOptionInfo.setText(paymentInfo[0]);

        for (String term : termsArr){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(term);
            checkBox.setTextSize(12);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (CheckBox cb : terms){
                        if (!cb.isChecked()){
                            cbAllAgree.setChecked(false);
                            return;
                        }
                    }
                    cbAllAgree.setChecked(true);
                }
            });
            terms.add(checkBox);
        }

        for (CheckBox termView : terms)
            llTerms.addView(termView);

        cbAllAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CheckBox cb : terms)
                    cb.setChecked(cbAllAgree.isChecked());
            }
        });

        for (int i = rentalStart; i <= rentalEnd; i++) {
            RentalTimeView timeView = new RentalTimeView(this, i);
            if (i <= rentalStart + rentalTime)
                timeView.setSelected(true);
            timeViews.add(timeView);
        }

        for (final RentalTimeView timeView : timeViews){
            timeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int hour = timeView.getHour();
                    if (rentalEnd - hour < rentalTime){
//                        for (int i = 0; i < ; i++) {
//
//                        }
                    }
                }
            });
            llRentalTime.addView(timeView);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClickInReservation(View view){
        switch (view.getId()){
            case R.id.iv_reservation_back:
                finish();
                break;

            case R.id.tv_btn_reservation_on_foot:
                if (comingBy != ON_FOOT){
                    ((TransitionDrawable)tvBtnOnFoot.getBackground()).startTransition(300);
                    tvBtnOnFoot.setTextColor(getColor(R.color.colorBackground));
                    if (comingBy != 0){
                        ((TransitionDrawable)tvBtnByVehicle.getBackground()).reverseTransition(300);
                        tvBtnByVehicle.setTextColor(getColor(R.color.colorButton));
                    }
                    comingBy = ON_FOOT;
                }
                break;

            case R.id.tv_btn_reservation_by_vehicle:
                if (comingBy != BY_VEHICLE){
                    ((TransitionDrawable)tvBtnByVehicle.getBackground()).startTransition(300);
                    tvBtnByVehicle.setTextColor(getColor(R.color.colorBackground));
                    if (comingBy != 0){
                        ((TransitionDrawable)tvBtnOnFoot.getBackground()).reverseTransition(300);
                        tvBtnOnFoot.setTextColor(getColor(R.color.colorButton));
                    }
                    comingBy = BY_VEHICLE;
                }
                break;

            case R.id.tv_btn_reservation_change_contact:
                changeContact = true;
                llReservationChange.setVisibility(View.VISIBLE);
                etContact.setText("010");
                view.setVisibility(View.GONE);
                etContact.setEnabled(changeContact);
                etContact.requestFocus();
                imm.showSoftInput(etContact, 0);
                break;

            case R.id.tv_btn_reservation_change_contact_cancel:
                changeContact = false;
                llReservationChange.setVisibility(View.GONE);
                //회원 전화번호 원상복구
                etContact.setEnabled(changeContact);
                findViewById(R.id.tv_btn_reservation_change_contact).setVisibility(View.VISIBLE);
                imm.hideSoftInputFromWindow(etContact.getWindowToken(), 0);
                break;

            case R.id.tv_btn_reservation_pay:
                break;
        }
    }
}