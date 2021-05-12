package com.darshan.roomcoroutine.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darshan.roomcoroutine.data.local.dao.RestImageDao
import com.darshan.roomcoroutine.data.local.dao.RestaurantDao
import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.data.local.entity.Restaurant

@Database(entities = [Restaurant::class, RestImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun restaurantImageDao(): RestImageDao
}