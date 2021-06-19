package com.mnhyim.moviecatalog.di

import com.mnhyim.core.domain.usecase.CatalogInteractor
import com.mnhyim.core.domain.usecase.CatalogUseCase
import com.mnhyim.moviecatalog.ui.viewmodels.CatalogViewModel
import com.mnhyim.moviecatalog.ui.viewmodels.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<CatalogUseCase> { CatalogInteractor(get()) }
}

val viewModelModule = module {
    viewModel { CatalogViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}