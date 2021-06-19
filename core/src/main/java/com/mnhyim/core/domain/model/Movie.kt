package com.mnhyim.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String,
    val backdropPath: String,
    var isFavorite: Boolean
) : Parcelable