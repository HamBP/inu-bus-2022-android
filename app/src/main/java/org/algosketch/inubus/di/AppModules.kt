package org.algosketch.inubus.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.repository.BusArrivalInfoRepositoryImpl
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    fun provideBusArrivalInfoRepository(
        cachedDataSource: CachedDataSource,
        remoteDataSource: RemoteDataSource,
    ): BusArrivalInfoRepository = BusArrivalInfoRepositoryImpl(
        cachedDataSource = cachedDataSource,
        remoteDataSource = remoteDataSource,
    )

    @Provides
    fun provideBusArrivalService(): BusArrivalService = RetrofitServiceFactory.create()
}