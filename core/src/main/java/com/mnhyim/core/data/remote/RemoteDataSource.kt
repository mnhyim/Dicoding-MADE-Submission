package com.mnhyim.core.data.remote

import android.util.Log
import com.mnhyim.core.data.remote.api.ApiService
import com.mnhyim.core.data.remote.response.ApiResponse
import com.mnhyim.core.data.remote.response.ApiResponse.*
import com.mnhyim.core.data.remote.response.MovieResponse
import com.mnhyim.core.utils.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val TAG: String = this::class.java.simpleName

    suspend fun discoverMovies(): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = apiService.discoverMovies(API_KEY)
                Log.d(TAG, "$response")
                val dataArray = response.results
                if (dataArray.isNotEmpty()) emit(Success(dataArray)) else emit(Empty)
            } catch (e: Exception) {
                emit(Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}