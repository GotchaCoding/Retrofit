package org.techtown.retrofit2

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("companys")
    val companys: List<ResultSearchMovies.Company> = listOf(),
    @SerializedName("directors")
    val directors: List<ResultSearchMovies.Director> = listOf(),
    @SerializedName("genreAlt")
    val genreAlt: String = "",
    @SerializedName("movieCd")
    val movieCd: String = "",
    @SerializedName("movieNm")
    val movieNm: String = "",
    @SerializedName("movieNmEn")
    val movieNmEn: String = "",
    @SerializedName("nationAlt")
    val nationAlt: String = "",
    @SerializedName("openDt")
    val openDt: String = "",
    @SerializedName("prdtStatNm")
    val prdtStatNm: String = "",
    @SerializedName("prdtYear")
    val prdtYear: String = "",
    @SerializedName("repGenreNm")
    val repGenreNm: String = "",
    @SerializedName("repNationNm")
    val repNationNm: String = "",
    @SerializedName("typeNm")
    val typeNm: String = "",
    var isFavorite: Boolean = false
)