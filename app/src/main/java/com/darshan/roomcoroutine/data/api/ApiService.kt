package com.darshan.roomcoroutine.data.api

import com.darshan.roomcoroutine.data.model.BaseResponse
import retrofit2.http.GET

interface ApiService {

    @GET("restaurants_list")
    suspend fun getRestaurants(): BaseResponse

}