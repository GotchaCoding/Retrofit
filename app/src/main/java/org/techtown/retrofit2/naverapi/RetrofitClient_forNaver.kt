package org.techtown.retrofit2.naverapi

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient_forNaver private constructor() {

    init {
        Log.e("log", "Client : RetrofitClient_forNaver 생성자 실행되며 retrofit 객체 생성")
        retrofit_naver = Retrofit.Builder() //Retrofit 객체생성
            .baseUrl(baseUrl_naver)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    companion object {
        var instance_naver: RetrofitClient_forNaver? = null
            get() { //확장함수
                Log.e("log", "Client_forNaver :companion objec  instance 확장함수")
                if (field == null) {
                    field = RetrofitClient_forNaver()
                }
                return field
            }
            private set
        @JvmStatic
        lateinit var retrofit_naver: Retrofit
        private set
        private const val baseUrl_naver = "https://openapi.naver.com"
    }



}