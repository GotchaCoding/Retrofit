package org.techtown.retrofit2.response


import com.google.gson.annotations.SerializedName

data class NaverMovie(
    @SerializedName("display")
    val display: Int = 0,
    @SerializedName("items")
    val items: List<NMItem> = listOf(),
    @SerializedName("lastBuildDate")
    val lastBuildDate: String = "",
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)