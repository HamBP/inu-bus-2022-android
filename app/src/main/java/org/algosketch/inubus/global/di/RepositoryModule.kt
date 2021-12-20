package org.algosketch.inubus.global.di

import org.algosketch.inubus.data.repository.LocalRepository
import org.algosketch.inubus.data.repository.RemoteRepository
import org.algosketch.inubus.global.configs.ServerConfigs
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { LocalRepository() }
    single { RemoteRepository() }
    single<MemoService> { Retrofit.Builder()
        .baseUrl(ServerConfigs.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MemoService::class.java)
    }
}
