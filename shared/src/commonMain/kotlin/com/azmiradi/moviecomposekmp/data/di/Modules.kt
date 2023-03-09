package com.azmiradi.moviecomposekmp.data.di

import com.azmiradi.moviecomposekmp.data.network.MovieApi
import com.azmiradi.moviecomposekmp.data.network.MovieApiImpl
import com.azmiradi.moviecomposekmp.data.repository.HomeRepository
import com.azmiradi.moviecomposekmp.domain.repository.HomeRepositoryImpl
import com.azmiradi.moviecomposekmp.domain.use_cases.*
import com.azmiradi.moviecomposekmp.presentation.screens.details_screen.MovieDetailsViewModel
import com.azmiradi.moviecomposekmp.presentation.screens.favorite.FavoriteMoviesViewModel
import com.azmiradi.moviecomposekmp.presentation.screens.home.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

object Modules {
    val useCases = module {
        factory<GetMoviesUseCase> { GetMoviesUseCase(get()) }
        factory<GetMovieDetailsUseCase> { GetMovieDetailsUseCase(get()) }
        factory<GetFavoriteMoviesUseCase> { GetFavoriteMoviesUseCase(get()) }
        factory<AddFavoriteMovieUseCase> { AddFavoriteMovieUseCase(get()) }
        factory<CheckIsSavedUseCase> { CheckIsSavedUseCase(get()) }
        factory<DeleteMovieUseCase> { DeleteMovieUseCase(get()) }
    }

    val repositories = module {
        factory<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    }

    val viewModels = module {
        factory<HomeViewModel> { HomeViewModel(get()) }
        factory<FavoriteMoviesViewModel> { FavoriteMoviesViewModel(get()) }
        factory<MovieDetailsViewModel> { MovieDetailsViewModel(get(), get(), get(), get()) }
    }

    val servicesAPIs = module {
        factory<MovieApi> { MovieApiImpl() }
    }

}

expect fun initKoin(): KoinApplication
