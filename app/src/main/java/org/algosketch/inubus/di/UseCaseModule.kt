package org.algosketch.inubus.di

import org.algosketch.inubus.domain.usecase.GetBusArrivalsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetBusArrivalsUseCase(get()) }
}