package com.darshan.roomcoroutine.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.darshan.roomcoroutine.data.local.entity.Restaurant

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant WHERE id=:id")
    suspend fun getRestaurantDetail(id: Int): Restaurant?

    @Query("SELECT * FROM restaurant")
    suspend fun getAll(): List<Restaurant>

    @Insert
    suspend fun insertAll(restaurants: List<Restaurant>)

    @Delete
    suspend fun delete(restaurant: Restaurant)

}