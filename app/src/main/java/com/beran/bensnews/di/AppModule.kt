package com.beran.bensnews.di

import com.beran.bensnews.ui.screen.explore.ExploreViewModel
import com.beran.bensnews.ui.screen.home.HomeViewModel
import com.beran.core.domain.usecase.NewsInteractor
import com.beran.core.domain.usecase.NewsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
}