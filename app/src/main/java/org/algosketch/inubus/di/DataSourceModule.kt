package org.algosketch.inubus.di

import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { CachedDataSource() }
    single { RemoteDataSource() }
    single { DummyDataSource() }
}