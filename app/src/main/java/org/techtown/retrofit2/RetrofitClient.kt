package org.techtown.retrofit2

import android.util.Log
import org.techtown.retrofit2.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    init {   //객체가 레퍼런스 대입되기 전에 생성자 실행 (객체 사용전 작업처리)
        Log.e("tag", "RetrofitClient 생성자 실행되며 retrofit 객체 생성")
        retrofit = Retrofit.Builder() //Retrofit 객체생성
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        var instance: RetrofitClient? = null
            get() {
                Log.e("tag", "Client :getInstance 메소드")
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }
            private set
        @JvmStatic
        var retrofit: Retrofit? = null
            private set
        private const val baseUrl = "http://www.kobis.or.kr"
    }
}