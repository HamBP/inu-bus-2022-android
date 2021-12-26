package org.algosketch.inubus.global.di

import org.algosketch.inubus.data.repository.RemoteRepository
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    // TODO get() 으로 바꿔야 함.
    factory { GetBusArrivalTimeUseCase(RemoteRepository()) }
}