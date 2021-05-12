package com.darshan.roomcoroutine.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRestaurants() = apiService.getRestaurants()
}