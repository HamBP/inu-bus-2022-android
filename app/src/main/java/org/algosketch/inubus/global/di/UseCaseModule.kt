package org.algosketch.inubus.global.di

import org.algosketch.inubus.data.repository.BusArrivalRepositoryImpl
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetBusArrivalTimeUseCase(BusArrivalRepositoryImpl(get(), get())) }
}