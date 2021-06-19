package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mnhyim.core.domain.usecase.CatalogUseCase

class CatalogViewModel(catalogUseCase: CatalogUseCase) : ViewModel() {

    val movies = catalogUseCase.getAllMovies().asLiveData()
}