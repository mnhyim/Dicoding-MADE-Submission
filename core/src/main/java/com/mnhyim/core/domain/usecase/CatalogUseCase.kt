package com.mnhyim.core.domain.usecase

import com.mnhyim.core.domain.model.Movie
import com.mnhyim.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CatalogUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getAllFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, status: Boolean)

}