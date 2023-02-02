package org.techtown.retrofit2.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.techtown.retrofit2.application.Retrofit2App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.attribute.AclEntry.newBuilder

class RetrofitClient private constructor() {

    init {   //객체가 레퍼런스 대입되기 전에 생성자 실행 (객체 사용전 작업처리)
        Log.e("log", "Client : RetrofitClient 생성자 실행되며 retrofit 객체 생성")

//        val client = okhttp3.OkHttpClient.Builder()
//
//        var baseParameterInterceptor : Interceptor = (object:Interceptor{
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val originalRequest = chain.request()
//                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("key",Retrofit2App.API_KEY)
//                val finalRequest = originalRequest.newBuilder()
//                    .url(addedUrl)
//                    .method(originalRequest.method, originalRequest.body)
//                    .build()
//                return chain.proceed(finalRequest)
//            }
//        })
//        client.addInterceptor(baseParameterInterceptor)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val headerInterceptor: Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Naver_Client_Id", Retrofit2App.clientId)
                    .addHeader("X-Naver_Client_Secret", Retrofit2App.clientSecret)
                    .build()
                return chain.proceed(request)
            }

        })
        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()


        retrofit = Retrofit.Builder() //Retrofit 객체생성
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitNaver = Retrofit.Builder() //Retrofit 객체생성
            .baseUrl(naverBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    companion object {
        //RetrofitClient 객체생성
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