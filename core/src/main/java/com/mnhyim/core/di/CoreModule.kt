package com.mnhyim.core.di

import androidx.room.Room
import com.mnhyim.core.data.CatalogRepository
import com.mnhyim.core.data.local.LocalDataSource
import com.mnhyim.core.data.local.room.CatalogRoomDatabase
import com.mnhyim.core.data.remote.RemoteDataSource
import com.mnhyim.core.data.remote.api.ApiService
import com.mnhyim.core.domain.repository.CatalogRepositoryInterface
import com.mnhyim.core.utils.Constants
import com.mnhyim.core.utils.Executors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CatalogRoomDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogRoomDatabase::class.java, "catalog.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { Executors() }
    single { CatalogRepository(get(), get(), get()) }
    single<CatalogRepositoryInterface> { CatalogRepository(get(), get(), get()) }
}
