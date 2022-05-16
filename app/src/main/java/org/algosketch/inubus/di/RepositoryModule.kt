package org.algosketch.inubus.di

import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.koin.dsl.module

val repositoryModule = module {
    single<BusArrivalService> { RetrofitServiceFactory.create() }
}
