package org.techtown.retrofit2.retrofit

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    init {   //객체가 레퍼런스 대입되기 전에 생성자 실행 (객체 사용전 작업처리)
        Log.e("log", "Client : RetrofitClient 생성자 실행되며 retrofit 객체 생성")
        retrofit = Retrofit.Builder() //Retrofit 객체생성
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitNaver = Retrofit.Builder() //Retrofit 객체생성
            .baseUrl(naverBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {//RetrofitClient 객체생성
        var instance: RetrofitClient? = null
            get() {  //확장함수
                Log.e("log", "Client :companion objec  instance 확장함수")
                if (field == null) {
                    field = RetrofitClient()   //RetrofitClient 객체생성
                }
                return field
            }
            private set
        @JvmStatic
        lateinit var retrofit: Retrofit
            private set
        private const val baseUrl = "http://www.kobis.or.kr"  //const 상수이며 자바의 static final 과 동일한 역할

        @JvmStatic
        lateinit var retrofitNaver: Retrofit
            private set
        private const val naverBaseUrl = "https://openapi.naver.com"
    }
}