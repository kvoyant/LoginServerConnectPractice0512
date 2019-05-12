package com.tjeit.loginserverconnectpractice0512.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

    //    어떤 파일 에 저장할지 경로를 담아 두는 변수
    private static final String prefName = "LoginServerPracticePref";

    //    이 메모에서 다루는 항목들의 리스트를 변수로 생성
    private static final String USER_INPUT_ID = "";
    private static final String USER_INPUT_PW = "";

//    실제로 각각의 항목을 저장 /불러오는 기능

//    setter : ID를 저장하는 id_setter

    public static void setUserInputId(Context context, String InputId) {
//        메모장 파일 (txt) 를 여는 작업.

        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);

//        실제로 데이터 저장하기
        pref.edit().putString(USER_INPUT_ID, inputId).apply();
    }

//        getter : 저장된 ID가 있다면 , 불러오기
    public static String getUserInputId(Context context) {
//        메모장 파일을 열어주는 작업
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);

        return  pref.getString(USER_INPUT_ID, "");

    }
}
