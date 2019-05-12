package com.tjeit.loginserverconnectpractice0512;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tjeit.loginserverconnectpractice0512.databinding.ActivityMainBinding;
import com.tjeit.loginserverconnectpractice0512.utils.ConnectServer;
import com.tjeit.loginserverconnectpractice0512.utils.ContextUtil;

import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    ActivityMainBinding act;

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
        act = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }
}
