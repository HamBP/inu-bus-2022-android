package org.algosketch.inubus.global.di

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.algosketch.inubus.data.repository.RemoteRepository
import org.algosketch.inubus.global.configs.ServerConfigs
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { RemoteRepository() }
    single<BusArrivalService> { Retrofit.Builder()
        .baseUrl(ServerConfigs.baseUrl)
        .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
        .client(OkHttpClientFactory.create())
        .build()
        .create(BusArrivalService::class.java)
    }
}
