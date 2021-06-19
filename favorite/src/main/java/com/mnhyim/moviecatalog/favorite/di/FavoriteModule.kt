package com.mnhyim.moviecatalog.favorite.di

import com.mnhyim.moviecatalog.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}