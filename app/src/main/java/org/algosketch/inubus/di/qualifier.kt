package org.algosketch.inubus.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BusArrivalInfoRemoteRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BusArrivalInfoDummyRepository