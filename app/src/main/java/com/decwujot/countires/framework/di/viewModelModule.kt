package com.decwujot.countires.framework.di

import com.decwujot.countires.framework.viewModel.CountriesViewModel
import com.decwujot.countires.framework.viewModel.DetailViewModel
import com.decwujot.countires.framework.viewModel.SplashScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashScreenViewModel() }
    viewModel { CountriesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}