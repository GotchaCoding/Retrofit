package org.techtown.retrofit2

import retrofit2.http.GET
import org.techtown.retrofit2.naverapi.NaverMovie
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getDailyMovies(
        @Query("key") key: String?,
        @Query("targetDt") targetDt: String?
    ): Call<Result>

    @GET("/kobisopenapi/webservice/rest/movie/searchMovieList.json")
    fun getSearchMovies(
        @Query("key") key: String,
        @Query("curPage") curPage: Int,
        @Query("itemPerPage") itemPerPage: Int,
        @Query("movieNm") movieNm: String
    ): Call<ResultSearchMovies>

    @GET("/v1/search/movie.json")
    fun naverMovies(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") title: String,
        @Query("display") display: Int
    ): Call<NaverMovie>
}