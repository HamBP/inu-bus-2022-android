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

    @Provides
    fun provideBusArrivalInfoRepository(
        cachedDataSource: CachedDataSource,
        remoteDataSource: RemoteDataSource,
    ): BusArrivalInfoRepository {
        // Mock 데이터를 얻으려면 false로 변경하세요.
        val isProduct = true

        return if(isProduct) BusArrivalInfoRepositoryImpl(
            cachedDataSource = cachedDataSource,
            remoteDataSource = remoteDataSource,
        ) else BusArrivalDummyInfoRepository(
            dummyDataSource = DummyDataSource(),
        )
    }

    @Provides
    fun provideBusArrivalService(): BusArrivalService = RetrofitServiceFactory.create()
}