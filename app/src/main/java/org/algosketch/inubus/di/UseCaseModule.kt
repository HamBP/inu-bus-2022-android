package org.algosketch.inubus.di

import org.algosketch.inubus.data.repository.BusArrivalRepositoryImpl
import org.algosketch.inubus.domain.usecase.GetBusArrivalTimeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetBusArrivalTimeUseCase(BusArrivalRepositoryImpl(get(), get())) }
}