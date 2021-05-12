package com.darshan.roomcoroutine.data.local

import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.data.local.entity.Restaurant

interface DatabaseHelper {

    suspend fun getRestaurantDetail(id: Int): Restaurant?

    suspend fun getRestaurants(): List<Restaurant>

    suspend fun insertAll(restaurants: List<Restaurant>)

    suspend fun getRestaurantImages(id: Int): List<RestImage>

    suspend fun getRestaurantImages(): List<RestImage>

    suspend fun insertAllRestImages(restImages: List<RestImage>)

}