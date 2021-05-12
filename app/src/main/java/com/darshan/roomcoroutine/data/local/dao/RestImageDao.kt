package com.darshan.roomcoroutine.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.darshan.roomcoroutine.data.local.entity.RestImage

@Dao
interface RestImageDao {

    @Query("SELECT * FROM restimage WHERE main_id = :mainId")
    suspend fun getRestImages(mainId: Int): List<RestImage>

    @Query("SELECT * FROM restimage")
    suspend fun getAll(): List<RestImage>

    @Insert
    suspend fun insertAll(restaurants: List<RestImage>)

    @Delete
    suspend fun delete(image: RestImage)

}