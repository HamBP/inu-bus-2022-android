package org.algosketch.inubus.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.repository.BusArrivalDummyInfoRepository
import org.algosketch.inubus.data.repository.BusArrivalInfoRepositoryImpl
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @BusArrivalInfoRemoteRepository
    @Provides
    fun provideBusArrivalInfoRepository1(
        cachedDataSource: CachedDataSource,
        remoteDataSource: RemoteDataSource,
    ): BusArrivalInfoRepository = BusArrivalInfoRepositoryImpl(
        cachedDataSource = cachedDataSource,
        remoteDataSource = remoteDataSource,
    )

    @BusArrivalInfoDummyRepository
    @Provides
    fun provideBusArrivalInfoDummyRepository(dummyDataSource: DummyDataSource): BusArrivalInfoRepository =
        BusArrivalDummyInfoRepository(dummyDataSource)

    @Provides
    fun provideBusArrivalService(): BusArrivalService = RetrofitServiceFactory.create()
}