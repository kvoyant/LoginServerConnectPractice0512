package com.tjeit.loginserverconnectpractice0512;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.tjeit.loginserverconnectpractice0512.databinding.ActivityLoginBinding;
import com.tjeit.loginserverconnectpractice0512.utils.ConnectServer;
import com.tjeit.loginserverconnectpractice0512.utils.ContextUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {
        act.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                1.아이디와 비번 입력을 받아오기
                String inputId = act.loginIdEdt.getText().toString();
                String inputPw = act.loginPwEdt.getText().toString();

//                1.2 입력받으 ID를 SharedPreferences 저장.
                ContextUtil.setUserInputId(mContext, inputId);

//                2.받아온 아이디와 비번이 정말로 올바른 회원인지? 검사
//                아이디 비번이 모두 동일한 사람이 회원명부에 있는지?
                ConnectServer.postRequestSignIn(mContext, inputId, inputPw, new ConnectServer.JsonResponsHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int code = json.getInt("code");

                                    if(code == 200) {
//                                      로그인 성공

                                    }
                                    else {
//                                        로그인 실패 alert dialog
                                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                        alert.setTitle("로그인 실패 알림");
                                        alert.setMessage(json.getString("message"));
                                        alert.setPositiveButton("확인", null);
                                        alert.show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

//                test123 / Test!123
//                iu001 / Test!123
            }
        });
    }

    @Override
    public void setValues() {
        String savedUserId = ContextUtil.getUserInputId(mContext);

        act.loginIdEdt.setText(savedUserId);
    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this,R.layout.activity_login);
    }
}
