package com.decwujot.countires.framework

import com.decwujot.countires.core.useCases.FetchAllCountries
import com.decwujot.countires.core.useCases.FetchCountryByAlphaCode

data class UseCases(
    val fetchAllCountries: FetchAllCountries,
    val fetchCountryByAlphaCode: FetchCountryByAlphaCode
)