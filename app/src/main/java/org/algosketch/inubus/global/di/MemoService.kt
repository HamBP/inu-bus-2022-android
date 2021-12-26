package org.algosketch.inubus.global.di

import org.algosketch.inubus.data.model.BusArrival
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MemoService {
    @GET(".")
    suspend fun getMemo(): Response<BusArrival>

    @POST(".")
    suspend fun writeMemo(
        @Body requestBody: BusArrival
    ): Response<Unit>
}