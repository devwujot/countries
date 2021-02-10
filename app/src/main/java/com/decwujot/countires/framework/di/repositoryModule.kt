package com.decwujot.countires.framework.di

import com.decwujot.countires.core.repository.RemoteRepository
import com.decwujot.countires.framework.RepositoryDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { RemoteRepository(RepositoryDataSourceImpl(get())) }
}