package org.techtown.retrofit2

import com.google.gson.annotations.SerializedName

data class ResultSearchMovies(
    @SerializedName("movieListResult")
    val movieListResult: MovieListResult = MovieListResult()
) {
    data class MovieListResult(
        @SerializedName("movieList")
        val movieList: List<Movie> = listOf(),
        @SerializedName("source")
        val source: String = "",
        @SerializedName("totCnt")
        val totCnt: Int = 0
    )

    data class Company(
        @SerializedName("companyCd")
        val companyCd: String = "",
        @SerializedName("companyNm")
        val companyNm: String = ""
    )

    data class Director(
        @SerializedName("peopleNm")
        val peopleNm: String = ""
    )
}