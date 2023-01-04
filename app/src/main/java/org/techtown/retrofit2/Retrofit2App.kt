package org.techtown.retrofit2

import android.app.Application

class Retrofit2App : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.instance //RetrofitClient 객체를 만듬(baseUrl과 convertFactory, build가 생성자로 작동하는)
    }

    companion object {
        const val API_KEY = "8d2474c237227f0f228d8afd41acc0d4"
    }
}