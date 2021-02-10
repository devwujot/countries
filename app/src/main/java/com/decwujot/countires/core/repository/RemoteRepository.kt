package com.decwujot.countires.core.repository

class RemoteRepository(private val dataSource: RemoteDataSource) {

    suspend fun fetchAllCountries() = dataSource.fetchAllCountries()

    suspend fun fetchCountryByAlphaCode(alphaCode: String) =
        dataSource.fetchCountryByAlphaCode(alphaCode)
}