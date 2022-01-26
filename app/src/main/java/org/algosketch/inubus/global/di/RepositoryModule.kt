package org.algosketch.inubus.global.di

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.global.configs.ServerConfigs
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { CachedDataSource() }
    single { RemoteDataSource() }
    single<BusArrivalService> { Retrofit.Builder()
        .baseUrl(ServerConfigs.baseUrl)
        .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
        .client(OkHttpClientFactory.create())
        .build()
        .create(BusArrivalService::class.java)
    }
}
