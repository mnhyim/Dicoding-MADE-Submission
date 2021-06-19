package com.mnhyim.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDiscoverResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)
