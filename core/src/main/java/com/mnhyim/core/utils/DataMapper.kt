package com.mnhyim.core.utils

import com.mnhyim.core.data.local.entity.MovieEntity
import com.mnhyim.core.data.remote.response.MovieResponse
import com.mnhyim.core.domain.model.Movie

object DataMapper {

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            isFavorite = input.isFavorite
        )
    }

    fun mapEntitiesToDomain(movies: List<MovieEntity>): List<Movie> {
        return movies.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapResponsesToEntities(movie: List<MovieResponse>): List<MovieEntity> {
        return movie.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = false
            )
        }
    }
}