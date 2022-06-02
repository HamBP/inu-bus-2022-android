package org.algosketch.inubus.di

import org.algosketch.inubus.domain.usecase.GetBusArrivalInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetBusArrivalInfoUseCase(get()) }
}