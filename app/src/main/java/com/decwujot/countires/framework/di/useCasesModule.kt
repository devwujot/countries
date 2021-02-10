package com.decwujot.countires.framework.di

import com.decwujot.countires.core.useCases.FetchAllCountries
import com.decwujot.countires.core.useCases.FetchCountryByAlphaCode
import com.decwujot.countires.framework.UseCases
import org.koin.dsl.module

val useCasesModule = module {
    single {
        UseCases(
            FetchAllCountries(get()),
            FetchCountryByAlphaCode(get())
        )
    }
}