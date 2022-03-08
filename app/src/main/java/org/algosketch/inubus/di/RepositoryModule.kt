package org.algosketch.inubus.di

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.algosketch.inubus.global.configs.ServerConfigs
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { RetrofitServiceFactory.create(BusArrivalService::class.java) }
}
