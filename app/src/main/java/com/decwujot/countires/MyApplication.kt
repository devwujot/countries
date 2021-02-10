package com.decwujot.countires

import android.app.Application
import androidx.multidex.MultiDex
import com.decwujot.countires.framework.di.networkModule
import com.decwujot.countires.framework.di.repositoryModule
import com.decwujot.countires.framework.di.useCasesModule
import com.decwujot.countires.framework.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    useCasesModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}