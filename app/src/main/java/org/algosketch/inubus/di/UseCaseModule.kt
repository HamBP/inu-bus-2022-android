package org.algosketch.inubus.di

import org.algosketch.inubus.data.repository.BusArrivalInfoRepositoryImpl
import org.algosketch.inubus.domain.usecase.GetBusArrivalInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetBusArrivalInfoUseCase(BusArrivalInfoRepositoryImpl(get(), get())) } // 실서버
//    single { GetBusArrivalTimeUseCase(BusArrivalDummyRepository()) } // mocking 서버
}