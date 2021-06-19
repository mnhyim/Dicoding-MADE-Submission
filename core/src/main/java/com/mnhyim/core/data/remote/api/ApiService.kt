package com.mnhyim.core.data.remote.api

import com.mnhyim.core.data.remote.response.MovieDiscoverResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieDiscoverResponse
}