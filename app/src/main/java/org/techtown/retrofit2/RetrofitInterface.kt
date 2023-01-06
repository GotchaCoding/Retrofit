package org.techtown.retrofit2

import retrofit2.http.GET
import org.techtown.retrofit2.ResultSearchMovies
import retrofit2.Call
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
}