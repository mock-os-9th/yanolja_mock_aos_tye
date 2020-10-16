package com.example.yanolkka.src.activities.reservation;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.main.MainActivity;
import com.example.yanolkka.src.activities.profile.ProfileService;
import com.example.yanolkka.src.activities.profile.interfaces.ProfileActivityView;
import com.example.yanolkka.src.activities.profile.interfaces.ProfileRetrofitInterface;
import com.example.yanolkka.src.activities.profile.models.Result;
import com.example.yanolkka.src.activities.profile.models.User;
import com.example.yanolkka.src.activities.profile.models.UserInfoResult;
import com.example.yanolkka.src.activities.reservation.interfaces.ReservationActivityView;
import com.example.yanolkka.src.activities.reservation.interfaces.ValidateListener;
import com.example.yanolkka.src.activities.reservation.models.ReservationInfo;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.views.RentalTimeView;
import com.example.yanolkka.src.common.views.TermView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yanolkka.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.yanolkka.src.ApplicationClass.getRetrofit;
import static com.example.yanolkka.src.ApplicationClass.sSharedPreferences;

public class ReservationActivity extends BaseActivity implements ValidateListener, TextWatcher, ReservationActivityView {
    private final int ON_FOOT = 1, BY_VEHICLE = 2;

    private LinearLayout llRentalTime, llReservationChange, llVisitor, llTerms;
    private TextView tvAccomName, tvRoomName, tvDateCheckIn, tvDayCheckIn,
            tvTimeCheckIn, tvDateCheckOut, tvDayCheckOut, tvTimeCheckOut,
            tvReservationPrice, tvDiscountPrice, tvFinalPrice, tvPayOption,
            tvPayOptionInfo, tvBtnOnFoot, tvBtnByVehicle, tvBtnFinishReservation;
    private EditText etReservationName, etContact, etVisitorName, etVisitorContact;
    private RadioGroup rgPayOptions;
    private CheckBox cbVisitor, cbAllAgree;
    private HorizontalScrollView svRentalTime;

    private SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat toFormat = new SimpleDateFormat("yyyy.MM.dd");

    private int accomIdx, roomIdx, price;
    private String startAt, endAt, accomName, roomName;
    private boolean isRental;

    private String[] paymentInfo, termsArr;
    private int payOption = 1, comingBy = 0;
    private boolean changeContact = false;
    private boolean signedIn;

    private String userName, contact;

    private List<CheckBox> terms = new ArrayList<>();
    private List<RentalTimeView> timeViews = new ArrayList<>();
    private int rentalStart = 10, rentalEnd = 23, rentalTime;

    private InputMethodManager imm;

    private ValidateListener validateListener = null;

    private void setValidateListener(ValidateListener listener){
        validateListener = listener;
    }

    @Override
    public void validCheck() {
        if (!etContact.getText().toString().isEmpty() && !etReservationName.getText().toString().isEmpty()
                && comingBy != 0 && cbAllAgree.isChecked()){
            if (cbVisitor.isChecked()){
                if (!etVisitorContact.getText().toString().isEmpty() && !etVisitorName.getText().toString().isEmpty()){
                    tvBtnFinishReservation.setEnabled(true);
                    tvBtnFinishReservation.setBackground(getDrawable(R.drawable.button_rect_accent));
                }
            } else{
                tvBtnFinishReservation.setEnabled(true);
                tvBtnFinishReservation.setBackground(getDrawable(R.drawable.button_rect_accent));
            }
        }else{
            tvBtnFinishReservation.setEnabled(false);
            tvBtnFinishReservation.setBackground(getDrawable(R.drawable.button_rect_gray));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        paymentInfo = getResources().getStringArray(R.array.paymentOptions);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        termsArr = getResources().getStringArray(R.array.reservationAgreementTerms);

        Intent fromIntent = getIntent();
        accomIdx = fromIntent.getIntExtra("accomIdx", 1);
        roomIdx = fromIntent.getIntExtra("roomIdx", 1);
        startAt = fromIntent.getStringExtra("startAt");
        endAt = fromIntent.getStringExtra("endAt");
        accomName = fromIntent.getStringExtra("accomName");
        roomName = fromIntent.getStringExtra("roomName");
        isRental = fromIntent.getBooleanExtra("isRental", false);
        price = fromIntent.getIntExtra("price", 30000);
        rentalTime = fromIntent.getIntExtra("rentalTime", 4);

        signedIn = sSharedPreferences.getString(X_ACCESS_TOKEN, null) != null;

        setViews();

        setListeners();
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

        svRentalTime = findViewById(R.id.sc_reservation_rental);
    }

    private void setListeners(){
        tvAccomName.setText(accomName);
        tvRoomName.setText(roomName);

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        tvReservationPrice.setText(decimalFormat.format(price)+"원");
        tvDiscountPrice.setText("0원");
        tvFinalPrice.setText(decimalFormat.format(price)+"원");
        tvBtnFinishReservation.setText(decimalFormat.format(price)+"원 결제하기");

        setValidateListener(this);

        try {
            Calendar start = Calendar.getInstance();
            start.setTime(fromFormat.parse(startAt));

            Calendar end = Calendar.getInstance();
            end.setTime(fromFormat.parse(endAt));

            String[] daysOfWeek = getResources().getStringArray(R.array.daysOfWeek);
            tvDayCheckIn.setText("("+daysOfWeek[start.get(Calendar.DAY_OF_WEEK)-1]+")");
            tvDayCheckOut.setText("("+daysOfWeek[end.get(Calendar.DAY_OF_WEEK)-1]+")");

            tvDateCheckIn.setText(toFormat.format(start.getTime()));
            tvDateCheckOut.setText(toFormat.format(end.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cbVisitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    llVisitor.setVisibility(View.VISIBLE);
                else
                    llVisitor.setVisibility(View.GONE);
            }
        });

        ((RadioButton)findViewById(R.id.rb_reservation_kakaopay)).setChecked(true);
        rgPayOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String title = "";
                String description = "";
                switch (i){
                    case R.id.rb_reservation_kakaopay:
                        title = getString(R.string.kakaopay);
                        description = paymentInfo[0];
                        payOption = 1;
                        break;

                    case R.id.rb_reservation_chai:
                        title = getString(R.string.chai);
                        description = paymentInfo[1];
                        payOption = 2;
                        break;

                    case R.id.rb_reservation_card:
                        title = getString(R.string.creditCard);
                        description = paymentInfo[2];
                        payOption = 3;
                        break;

                    case R.id.rb_reservation_phone:
                        title = getString(R.string.phonePay);
                        description = paymentInfo[3];
                        payOption = 4;
                        break;
                }
                tvPayOption.setText(title);
                tvPayOptionInfo.setText(description);
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
                            validateListener.validCheck();
                            return;
                        }
                    }
                    cbAllAgree.setChecked(true);
                    validateListener.validCheck();
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
                validateListener.validCheck();
            }
        });

        if (!isRental)
            llRentalTime.setVisibility(View.GONE);
        else{
            int scrollX = 0;
            for (int i = rentalStart; i <= rentalEnd; i++) {
                RentalTimeView timeView = new RentalTimeView(this, i);
                int hourNow = timeView.getHourNow();
                if (!timeView.isEnabled()){
                    scrollX += dpToPx(58);
                    svRentalTime.scrollBy(scrollX, 0);
                }
                if (i > hourNow && i <= hourNow+1 + rentalTime)
                    timeView.setSelected(true);
                timeViews.add(timeView);
                if (i == hourNow+1)
                    tvTimeCheckIn.setText(timeView.getHour()+":00");
                if (i == hourNow+1+rentalTime)
                    tvTimeCheckOut.setText(timeView.getHour()+":00");
                if (hourNow+1+rentalTime > rentalEnd)
                    tvTimeCheckOut.setText(rentalEnd+":00");
            }

            for (final RentalTimeView timeView : timeViews){
                timeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timeView.isEnabled()){
                            int hour = timeView.getHour();
                            if (hour > rentalEnd - rentalTime){
                                tvTimeCheckIn.setText(rentalEnd-rentalTime+":00");
                                tvTimeCheckOut.setText(rentalEnd+":00");
                                for (RentalTimeView rtv : timeViews){
                                    if (rtv.isEnabled()){
                                        if (rtv.getHour() >= rentalEnd - rentalTime)
                                            rtv.setSelected(true);
                                        else
                                            rtv.setSelected(false);
                                    }
                                }
                            }else{
                                tvTimeCheckIn.setText(hour+":00");
                                tvTimeCheckOut.setText(hour+rentalTime+":00");
                                for (RentalTimeView rtv : timeViews){
                                    if (rtv.isEnabled()){
                                        if (rtv.isEnabled() && rtv.getHour() >= hour && rtv.getHour() <= hour + rentalTime)
                                            rtv.setSelected(true);
                                        else
                                            rtv.setSelected(false);
                                    }
                                }
                            }
                        }
                    }
                });
                llRentalTime.addView(timeView);
            }
        }

        if (signedIn){
            ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);
            profileRetrofitInterface.getUserInfo().enqueue(new Callback<UserInfoResult>() {
                @Override
                public void onResponse(Call<UserInfoResult> call, Response<UserInfoResult> response) {
                    if (response.isSuccessful()){
                        UserInfoResult result = response.body();
                        if (result != null) {
                            if (result.isSuccess()) {
                                User user = result.getResult().getUser().get(0);
                                contact = user.getUserContact().substring(1);
                                userName = user.getUserName();
                                etContact.setText(contact);
                                etReservationName.setText(userName);
                            } else {
                                showCustomToast("사용자 정보를 불러오지 못했습니다.");
                            }
                        }else{
                            showCustomToast("사용자 정보를 불러오지 못했습니다.");
                        }
                    }else{
                        showCustomToast("사용자 정보를 불러오지 못했습니다.");
                    }
                }

                @Override
                public void onFailure(Call<UserInfoResult> call, Throwable t) {
                    showCustomToast("사용자 정보를 불러오지 못했습니다.");
                }
            });
        }

        etContact.addTextChangedListener(this);
        etReservationName.addTextChangedListener(this);
        etVisitorContact.addTextChangedListener(this);
        etVisitorName.addTextChangedListener(this);


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
                    validateListener.validCheck();
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
                    validateListener.validCheck();
                }
                break;

            case R.id.tv_btn_reservation_change_contact:
                changeContact = true;
                llReservationChange.setVisibility(View.VISIBLE);
                etContact.setText("010");
                etContact.setSelection(etContact.getText().length());
                view.setVisibility(View.GONE);
                etContact.setEnabled(changeContact);
                etContact.requestFocus();
                imm.showSoftInput(etContact, 0);
                break;

            case R.id.tv_btn_reservation_change_contact_cancel:
                changeContact = false;
                llReservationChange.setVisibility(View.GONE);
                //회원 전화번호 원상복구
                etContact.setText(contact);
                etContact.setEnabled(changeContact);
                findViewById(R.id.tv_btn_reservation_change_contact).setVisibility(View.VISIBLE);
                imm.hideSoftInputFromWindow(etContact.getWindowToken(), 0);
                break;

            case R.id.tv_btn_reservation_pay:
                String checkIn = tvDateCheckIn.getText().toString().replace(".", "-");
                checkIn += " "+tvTimeCheckIn.getText().toString()+":00";
                String checkOut = tvDateCheckOut.getText().toString().replace(".", "-");
                checkOut += " "+tvTimeCheckOut.getText().toString()+":00";

                String visit = cbVisitor.isChecked() ? "Y" : "N";

                String transportation = comingBy == BY_VEHICLE ? "C" : "W";

                ReservationInfo reservationInfo = new ReservationInfo(accomIdx, roomIdx, checkIn, checkOut,
                        etReservationName.getText().toString(), etContact.getText().toString(), visit, transportation, price);

                ReservationService reservationService = new ReservationService(this);
                showProgressDialog();
                reservationService.getReservationResult(reservationInfo);
                break;
        }
    }

    private int dpToPx(float dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
        return px;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        validateListener.validCheck();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void validateSuccess(String text) {
        showCustomToast(text);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        showCustomToast(message == null ? getString(R.string.networkError) : message);
        hideProgressDialog();
    }
}