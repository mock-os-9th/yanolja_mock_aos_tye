package com.example.yanolkka.src.activities.sign_up;

import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yanolkka.R;
import com.example.yanolkka.src.BaseActivity;
import com.example.yanolkka.src.activities.sign_up.fragments.SignUp1Fragment;
import com.example.yanolkka.src.activities.sign_up.interfaces.SignUpRetrofitInterface;
import com.example.yanolkka.src.activities.sign_up.models.SignUp;
import com.example.yanolkka.src.activities.sign_up.models.SignUpResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.yanolkka.src.ApplicationClass.getRetrofit;

public class SignUpActivity extends BaseActivity {

    public String email, pw, phoneNum;

    private Retrofit retrofit = getRetrofit();
    private SignUpRetrofitInterface userClient = retrofit.create(SignUpRetrofitInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_sign_up_fragments, SignUp1Fragment.newInstance()).commit();
    }

    public void requestSignUp(){
        SignUp signUp = new SignUp(email, pw, phoneNum);

        Log.d("TAGTAG", "\nsignIn : \nid : "+signUp.getUserId()
                +"\npw : "+signUp.getUserPwd()
                +"\nname : " +signUp.getUserName()
                +"\nbirth : "+signUp.getUserBirth()
                +"\ncontact : "+signUp.getUserContact()
                +"\ngender : "+signUp.getUserGender());

        Call<SignUpResult> call = userClient.signUp(signUp);

        call.enqueue(new Callback<SignUpResult>() {
            @Override
            public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                if (response.isSuccessful()){
                    SignUpResult signUpResult = response.body();
                    Log.d("TAGTAG", "response : "
                            +"\nisSuccess : "+ signUpResult.isSuccess()
                            +"\ncode : "+ signUpResult.getCode()
                            +"\nmessage : "+ signUpResult.getMessage());
                    Toast.makeText(SignUpActivity.this, "Success! : "+ signUpResult.getMessage(), Toast.LENGTH_SHORT).show();

                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this, "sign in failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResult> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "sign in error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}