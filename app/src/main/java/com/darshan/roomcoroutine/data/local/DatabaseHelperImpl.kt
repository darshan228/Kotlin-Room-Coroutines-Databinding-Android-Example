package com.darshan.roomcoroutine.data.local

import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.data.local.entity.Restaurant

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getRestaurantDetail(id: Int) =
        appDatabase.restaurantDao().getRestaurantDetail(id)

    override suspend fun getRestaurants(): List<Restaurant> = appDatabase.restaurantDao().getAll()

    override suspend fun insertAll(restaurants: List<Restaurant>) =
        appDatabase.restaurantDao().insertAll(restaurants)

    override suspend fun getRestaurantImages(id: Int): List<RestImage> =
        appDatabase.restaurantImageDao().getRestImages(id)

    override suspend fun getRestaurantImages(): List<RestImage> =
        appDatabase.restaurantImageDao().getAll()

    override suspend fun insertAllRestImages(images: List<RestImage>) =
        appDatabase.restaurantImageDao().insertAll(images)

}