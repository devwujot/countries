package com.decwujot.countires.framework

import com.decwujot.countires.core.data.model.Country
import com.decwujot.countires.core.repository.RemoteDataSource
import com.decwujot.countires.framework.network.CountriesApi
import retrofit2.Response

class RepositoryDataSourceImpl(
    private val remoteSource: CountriesApi
) : RemoteDataSource {

    override suspend fun fetchAllCountries(): Response<List<Country>> =
        remoteSource.fetchAllCountries()

    override suspend fun fetchCountryByAlphaCode(alphaCode: String): Response<Country> =
        remoteSource.fetchCountryByAlphaCode(alphaCode)
}