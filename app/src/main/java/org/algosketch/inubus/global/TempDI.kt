package org.algosketch.inubus.global

import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.repository.BusArrivalInfoRepositoryImpl
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.algosketch.inubus.domain.usecase.GetBusArrivalsUseCase

object TempDI {
    val cachedDataSource = CachedDataSource()
    val remoteDataSource = RemoteDataSource()
    val dummyCachedDataSource = DummyDataSource()

    val busArrivalService: BusArrivalService// = RetrofitServiceFactory.create<BusArrivalService>()

    val busArrivalRepository = BusArrivalInfoRepositoryImpl(cachedDataSource, remoteDataSource)
//    val busArrivalRepository = if(BuildConfig.IS_PROD) BusArrivalInfoRepositoryImpl(cachedDataSource, remoteDataSource)
//        else BusArrivalDummyInfoRepository()

    val getBusArrivalsUseCase = GetBusArrivalsUseCase(busArrivalRepository)

    init {
        busArrivalService = RetrofitServiceFactory.create<BusArrivalService>()
        println(busArrivalService)
    }
}