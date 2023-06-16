package me.algosketch.data.datasource.remote

import me.algosketch.data.BuildConfig
import me.algosketch.data.datasource.model.response.BusArrivalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArrivalService {
    @GET("getAllRouteBusArrivalList")
    suspend fun getBusArrivalTime(
        @Query("serviceKey", encoded = true)serviceKey: String = BuildConfig.API_KEY,
        @Query("pageNo")pageNo: Int = 1,
        @Query("numOfRows")numOfRows: Int = 20,
        @Query("bstopId")bstopId: String = "164000395",
    ): Response<BusArrivalResponse>
}