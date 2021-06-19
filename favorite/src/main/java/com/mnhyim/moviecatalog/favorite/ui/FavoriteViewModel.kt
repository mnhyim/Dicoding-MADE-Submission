package com.mnhyim.moviecatalog.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mnhyim.core.domain.usecase.CatalogUseCase

class FavoriteViewModel(private val catalogUseCase: CatalogUseCase) : ViewModel() {

    val movies = catalogUseCase.getAllFavoriteMovies().asLiveData()
}