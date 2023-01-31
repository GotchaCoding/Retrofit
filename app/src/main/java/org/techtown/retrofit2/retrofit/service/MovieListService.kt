package org.techtown.retrofit2.retrofit.service

import org.techtown.retrofit2.response.ResultSearchMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
* 영화 진흥원 API
* */
interface MovieListService {

    @GET("/kobisopenapi/webservice/rest/movie/searchMovieList.json")
    fun getSearchMovies(
        @Query("key") key: String,
        @Query("curPage") curPage: Int,
        @Query("itemPerPage") itemPerPage: Int,
        @Query("movieNm") movieNm: String
    ): Call<ResultSearchMovies>

}