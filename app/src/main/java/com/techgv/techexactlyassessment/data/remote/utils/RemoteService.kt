package com.techgv.techexactlyassessment.data.remote.utils

import com.techgv.techexactlyassessment.data.remote.dto.ProductResponseDto
import retrofit2.http.POST
import retrofit2.http.Query

interface RemoteService {

    @POST("apps/list")
    suspend fun getAppList(@Query("kid_id") kidId: Int = 378): ProductResponseDto
}