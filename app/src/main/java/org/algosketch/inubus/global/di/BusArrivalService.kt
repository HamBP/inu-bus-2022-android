package org.algosketch.inubus.global.di

import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.global.configs.ApiTokens
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArrivalService {
    @GET("getAllRouteBusArrivalList")
    suspend fun getBusArrivalTime(
        @Query("serviceKey", encoded = true)serviceKey: String = ApiTokens.arrivalServiceToken,
        @Query("pageNo")pageNo: Int = 1,
        @Query("numOfRows")numOfRows: Int = 10,
        @Query("bstopId")bstopId: Int = 165000111,
    ): Response<BusArrivalResponse>
}