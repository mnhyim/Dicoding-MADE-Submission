package com.mnhyim.core.domain.usecase

import com.mnhyim.core.domain.model.Movie
import com.mnhyim.core.domain.repository.CatalogRepositoryInterface
import com.mnhyim.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class CatalogInteractor(private val movieRepository: CatalogRepositoryInterface) : CatalogUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return movieRepository.getAllFavoriteMovies()
    }

    override fun setFavoriteMovie(movie: Movie, status: Boolean) {
        return movieRepository.setFavoriteMovie(movie, status)
    }
}