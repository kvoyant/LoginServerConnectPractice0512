package com.tjeit.loginserverconnectpractice0512;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tjeit.loginserverconnectpractice0512.databinding.ActivityMainBinding;
import com.tjeit.loginserverconnectpractice0512.utils.ConnectServer;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;
    LayoutInflater inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//      로그인에 성공한 사람의 토큰 받아오기

        String token = getIntent().getStringExtra("userToken");
        Log.d("사용자 토큰값", token);

//        받아온 토큰을 가지고 /v2/me_info api 호출, 사용자 데이터 표시
        ConnectServer.getRequestMeInfo(mContext, token, new ConnectServer.JsonResponsHandler() {
            @Override
            public void onResponse(JSONObject json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int code = json.getInt("code");

                            if (code == 200) {
//                                정상수신
//                                JSONObject data = json.getJSONObject("data");
//                                JSONObject userInfo = data.getJSONObject("user");
//
//                                String user_id = userInfo.getString("user_id");
//                                //보유금액
//                                String bankName = userInfo.getString("name");//거래은행이름
//                                String billing_account = userInfo.getString("billing_account");//계조번호
//
//                                Log.d("user_id", user_id);
//
//                                View view = inf.inflate(R.layout.activity_main, null);
//
//                                View row = convertView;
//                                TextView bankAccoutTxt = view.findViewById(R.id.bankAccountTxt);


//                                정상적으로 데이터 수신

                                JSONObject data = json.getJSONObject("data");

                                JSONObject user = data.getJSONObject("user");

//                                프사 경로
                                String profile_image = user.getString("profile_image");
//                                사용자 이름
                                String name = user.getString("name");
//                                보유 잔고
                                int balance = user.getInt("balance");

//                                은행 로고를 따기 전에 bank_code JSONObject 부터
                                JSONObject bank_code = user.getJSONObject("bank_code");

//                                은행 정보 안에 있는 로고 경로

                                String logo = bank_code.getString("logo");
//                                 은행 이름
                                String bankName = bank_code.getString("name");

                                String billing_account = user.getString("billing_account");


//                                프로필 이미지 출력
                                Glide.with(mContext).load(profile_image).into(act.profileImg);

//                                사용자 이름 출력

                                act.userNameTxt.setText(name);

//                                사용자 보유 잔고를 ,찍는 양식으로 출력
                                act.userBalnceTxt.setText(String.format("%,d P", balance));

//                                  은행 로고 출력

                                Glide.with(mContext).load(logo).into(act.bankLogoImg);

//                                은행 이름 출력

                                act.bankNameTxt.setText(bankName);

//                                사용자 은행 계좌 출력

                                act.bankAccountTxt.setText(billing_account);


                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
