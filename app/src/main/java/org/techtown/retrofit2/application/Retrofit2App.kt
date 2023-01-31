package org.techtown.retrofit2.application

import android.app.Application
import android.util.Log
import org.techtown.retrofit2.retrofit.RetrofitClient

class Retrofit2App : Application() {

    override fun onCreate() {
        Log.e("log" , "Retrofit2App : onCreate 실행 ")
        super.onCreate()
        RetrofitClient.instance //RetrofitClient 객체를 만듬(baseUrl과 convertFactory, build가 생성자로 작동하는)
    }

    companion object { //동반객체는 클래스 이름, 메서드이름만으로 더 간단히 메서들를 호출함.(객체이름 필요없음)   /  클래스와 동반객체는 하나처럼 움직이기에 다양한 기능을 클래스 이름으로 처리할 수 있음

        const val API_KEY = "8d2474c237227f0f228d8afd41acc0d4"

        const val clientId = "V9wXin1W5WiiOvMm2Q5W"
        const val clientSecret = "SFmyAkljOY"


    }
}