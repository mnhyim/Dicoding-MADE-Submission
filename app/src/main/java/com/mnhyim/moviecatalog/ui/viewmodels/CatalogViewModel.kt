package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mnhyim.core.domain.usecase.CatalogUseCase

class CatalogViewModel(private val catalogUseCase: CatalogUseCase) : ViewModel() {

    val movies = catalogUseCase.getAllMovies().asLiveData()
}