package com.mnhyim.core.data.local

import com.mnhyim.core.data.local.entity.MovieEntity
import com.mnhyim.core.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMoviesFromDB(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    fun getAllFavoritedMoviesFromDB(): Flow<List<MovieEntity>> {
        return movieDao.getAllFavoriteMovies()
    }

    suspend fun insertMoviesToDB(movies: List<MovieEntity>) {
        movieDao.insert(movies)
    }

    fun changeMovieFavoritedStatus(movie: MovieEntity, status: Boolean) {
        movie.isFavorite = status
        movieDao.update(movie)
    }
}