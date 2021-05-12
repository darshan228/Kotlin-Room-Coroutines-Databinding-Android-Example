package com.darshan.roomcoroutine.data.repository

import com.darshan.roomcoroutine.data.api.ApiHelper
import com.darshan.roomcoroutine.data.local.DatabaseHelper

class MainRepository(
    internal val apiHelper: ApiHelper,
    internal val dbHelper: DatabaseHelper
) {

    suspend fun getRestaurants() = apiHelper.getRestaurants()
}