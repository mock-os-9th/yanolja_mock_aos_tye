package com.example.yanolkka.src.common.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yanolkka.R;

import java.util.regex.Pattern;

@SuppressLint("ResourceAsColor")
public class ValidatingEditText extends RelativeLayout {
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_EMAIL = 1;
    public static final int STYLE_PASSWORD = 2;

    private Context mContext;
    private int style;
    private String hint;
    private EditText editText;
    private ImageView ivBtn;
    private TextView textView;

    private boolean valid = false;

    private String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    private String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public EventListener checkListener;

    public void setEventListener(EventListener l){checkListener = l;}

    public interface EventListener{
        void onTextChanged();
    }

    public ValidatingEditText(Context context, int style, String hint) {
        super(context);
        this.mContext = context;
        this.style = style;
        this.hint = hint;
        init();
    }

    public ValidatingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_validating_edit_text
                , this, true);

        editText = findViewById(R.id.et_validating);
        ivBtn = findViewById(R.id.iv_btn_validating);
        textView = findViewById(R.id.tv_validating);

        editText.setHighlightColor(R.color.textNormal);
        editText.setHint(hint);
        editText.setMaxLines(1);
        ivBtn.setVisibility(GONE);

        switch (style){
            case STYLE_NORMAL:
                ivBtn.setImageResource(R.drawable.ic_baseline_cancel_24);
                ivBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearText();
                    }
                });
                break;

            case STYLE_EMAIL:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                ivBtn.setImageResource(R.drawable.ic_baseline_cancel_24);
                ivBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearText();
                    }
                });
                break;

            case STYLE_PASSWORD:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ivBtn.setImageResource(R.drawable.btn_invisible);
                ivBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getTransformationMethod() instanceof PasswordTransformationMethod){
                            ivBtn.setImageResource(R.drawable.btn_visible);
                            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }else{
                            ivBtn.setImageResource(R.drawable.btn_invisible);
                            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        editText.setSelection(getText().length());
                    }
                });
                break;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!editText.getText().toString().isEmpty()){
                    ivBtn.setVisibility(VISIBLE);
                }else{
                    ivBtn.setVisibility(GONE);
                }
                checkListener.onTextChanged();
                hide();

                valid = (style == STYLE_EMAIL && Pattern.matches(emailPattern, getText()))
                        || (style == STYLE_PASSWORD && Pattern.matches(pwPattern, getText()))
                        || (style == STYLE_NORMAL && !getText().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String str = getText();

                if (!b){
                    ivBtn.setVisibility(GONE);

                    switch (style){
                        case STYLE_NORMAL:
                            if (!str.isEmpty())
                                valid = true;
                            else
                                valid = false;
                            break;

                        case STYLE_EMAIL:
                            if (str.isEmpty()){
                                setWarning("이메일을 입력해주세요.");
                                valid = false;
                            }else if (!Pattern.matches(emailPattern, str)){
                                setWarning("올바르지 않는 이메일 형식입니다.");
                                valid = false;
                            }else{
                                valid = true;
                                hide();
                            }
                            break;

                        case STYLE_PASSWORD:
                            if (str.isEmpty()){
                                setWarning("비밀번호를 입력해주세요.");
                                valid = false;
                            }else if(!Pattern.matches(pwPattern, str)){
                                setWarning("공백을 제외한 8자 이상, 영문, 숫자, 특수문자 조합으로 설정해주세요.");
                                valid = false;
                            }else{
                                valid = true;
                                hide();
                            }
                            break;
                    }
                }else{
                    if (!str.isEmpty())
                        ivBtn.setVisibility(VISIBLE);
                }
            }
        });
    }

    public boolean isValid(){
        return valid;
    }

    public String getText(){
        return editText.getText().toString();
    }

    public void clearText(){
        editText.setText("");
        ivBtn.setVisibility(GONE);
    }

    public void setText(String str){
        editText.setText(str);
    }

    private void setWarning(String warning){
        textView.setVisibility(VISIBLE);
        textView.setText(warning);
        editText.setHighlightColor(R.color.colorAccent);
    }

    private void hide(){
        textView.setText("");
        textView.setVisibility(INVISIBLE);
        editText.setHighlightColor(R.color.textNormal);
    }
}
