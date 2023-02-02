package org.techtown.retrofit2.model

import android.widget.ImageView

data class Movie(
    val title: String = "",
    val year: String = "",
    var isFavorite: Boolean = false,
) {
    lateinit var rank: String
    lateinit var director: String
    lateinit var actor: String
    lateinit var imageh: String

    constructor(
        title: String,
        year: String,
        rank: String,
        director: String,
        actor: String,
        imageh: String
    ) : this(
        title, year
    ) {
        this.rank = rank
        this.director = director
        this.actor = actor
        this.imageh = imageh

    }
}