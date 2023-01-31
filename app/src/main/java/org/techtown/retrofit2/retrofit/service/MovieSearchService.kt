package org.techtown.retrofit2.retrofit.service

import org.techtown.retrofit2.response.NaverMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/*
* Naver API
* */
interface MovieSearchService {

    @GET("/v1/search/movie.json")
    fun naverMovies(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") title: String,
        @Query("display") display: Int
    ): Call<NaverMovie>
}