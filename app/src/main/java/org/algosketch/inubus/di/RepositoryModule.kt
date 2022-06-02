package org.algosketch.inubus.di

import org.algosketch.inubus.BuildConfig.IS_PROD
import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.data.repository.BusArrivalDummyInfoRepository
import org.algosketch.inubus.data.repository.BusArrivalInfoRepositoryImpl
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BusArrivalService> { RetrofitServiceFactory.create() }
    if(IS_PROD) single<BusArrivalInfoRepository> { BusArrivalInfoRepositoryImpl(get(), get()) }
    else single<BusArrivalInfoRepository> { BusArrivalDummyInfoRepository() }
}
