package com.decwujot.countires.framework.di

import android.content.Context
import android.util.Log
import com.decwujot.countires.BuildConfig
import com.decwujot.countires.framework.network.CountriesApi
import com.decwujot.countires.framework.network.MockInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val BASE_URL = "https://restcountries.eu/rest/v2/"

    single {
        createMockInterceptor(get())
    }

    single {
        createOkHttpClient(get())
    }

    single {
        createRetrofit(get(), BASE_URL)
    }

    single {
        get<Retrofit>().create(CountriesApi::class.java)
    }

}

private fun createMockInterceptor(context: Context): MockInterceptor {
    return MockInterceptor(context)
}

private fun createOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
    if (BuildConfig.FLAVOR == "apiProduction") {
        return OkHttpClient.Builder()
            .build()
    } else {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()
    }
}

private fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit.Builder()
    .callFactory(okHttpClient)
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()