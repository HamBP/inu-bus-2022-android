package org.algosketch.inubus.global.di

import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.usecase.GetMemoUseCase
import org.algosketch.inubus.global.usecase.WriteMemoUseCase
import org.koin.dsl.module

val useCaseModule = module {
//    factory { GetMemoUseCase(Store.repository.value) }
//    factory { WriteMemoUseCase(Store.repository.value) }
}