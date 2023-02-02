package org.techtown.retrofit2.retrofit

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OkHttpClient {
    val TAG :String = "OkHttpClient"

    fun String?.isJsonObject():Boolean{
        return this?.startsWith("{") ==true && this.endsWith("}")
    }
    fun String?.isJsonArray():Boolean{
        return this?.startsWith("[") ==true && this.endsWith("]")
    }

}