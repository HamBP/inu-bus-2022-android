package org.algosketch.inubus.global.di

import org.algosketch.inubus.data.model.Memo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MemoService {
    @GET(".")
    suspend fun getMemo(): Response<Memo>

    @POST(".")
    suspend fun writeMemo(
        @Body requestBody: Memo
    ): Response<Unit>
}