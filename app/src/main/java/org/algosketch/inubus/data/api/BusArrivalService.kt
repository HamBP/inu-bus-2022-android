package org.algosketch.inubus.data.api

import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.common.configs.ApiTokens
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArrivalService {
    @GET("getAllRouteBusArrivalList")
    suspend fun getBusArrivalTime(
        @Query("serviceKey", encoded = true)serviceKey: String = ApiTokens.arrivalServiceToken,
        @Query("pageNo")pageNo: Int = 1,
        @Query("numOfRows")numOfRows: Int = 20,
        @Query("bstopId")bstopId: String = "164000395",
    ): Response<BusArrivalResponse>
}