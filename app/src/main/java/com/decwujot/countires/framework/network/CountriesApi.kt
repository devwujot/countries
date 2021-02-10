package com.decwujot.countires.framework.network

import com.decwujot.countires.core.data.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("all")
    suspend fun fetchAllCountries(): Response<List<Country>>

    @GET("alpha/{alphaCode}")
    suspend fun fetchCountryByAlphaCode(@Path("alphaCode") alphaCode: String): Response<Country>
}