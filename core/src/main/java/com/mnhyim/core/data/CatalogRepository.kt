package com.mnhyim.core.data

import android.util.Log
import com.mnhyim.core.data.local.LocalDataSource
import com.mnhyim.core.data.remote.RemoteDataSource
import com.mnhyim.core.data.remote.response.ApiResponse
import com.mnhyim.core.data.remote.response.MovieResponse
import com.mnhyim.core.domain.model.Movie
import com.mnhyim.core.domain.repository.CatalogRepositoryInterface
import com.mnhyim.core.utils.DataMapper.mapDomainToEntity
import com.mnhyim.core.utils.DataMapper.mapEntitiesToDomain
import com.mnhyim.core.utils.DataMapper.mapResponsesToEntities
import com.mnhyim.core.utils.Executors
import com.mnhyim.core.utils.NetworkBoundResource
import com.mnhyim.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val executors: Executors
) : CatalogRepositoryInterface {

    private val TAG: String = this::class.java.simpleName

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                Log.d(TAG, "loadFromDB")
                return localDataSource.getAllMoviesFromDB().map { mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                Log.d(TAG, "shouldfetch: ${data == null || data.isEmpty()}")
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                Log.d(TAG, "createCall")
                return remoteDataSource.discoverMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                Log.d(TAG, "saveCallResult: $data")
                val moviesList = mapResponsesToEntities(data)
                localDataSource.insertMoviesToDB(moviesList)
            }

        }.asFlow()

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getAllFavoritedMoviesFromDB().map { mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, status: Boolean) {
        executors.diskIO().execute {
            localDataSource.changeMovieFavoritedStatus(mapDomainToEntity(movie), status)
        }
    }
}