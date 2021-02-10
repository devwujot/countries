package com.decwujot.countires.core.repository

import com.decwujot.countires.core.data.model.Country
import retrofit2.Response

interface RemoteDataSource {

    suspend fun fetchAllCountries(): Response<List<Country>>

    suspend fun fetchCountryByAlphaCode(alphaCode: String): Response<Country>
}