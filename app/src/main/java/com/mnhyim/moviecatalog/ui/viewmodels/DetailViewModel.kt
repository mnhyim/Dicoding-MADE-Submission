package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.mnhyim.core.domain.model.Movie
import com.mnhyim.core.domain.usecase.CatalogUseCase

class DetailViewModel(private val catalogUseCase: CatalogUseCase) : ViewModel() {

    fun setFavorite(movie: Movie, status: Boolean) {
        return catalogUseCase.setFavoriteMovie(movie, status)
    }
}