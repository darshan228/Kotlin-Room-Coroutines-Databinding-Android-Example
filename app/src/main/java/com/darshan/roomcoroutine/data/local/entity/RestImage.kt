package com.darshan.roomcoroutine.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RestImage(
    @PrimaryKey var id: Int = 0,
    @ColumnInfo var main_id: Int = 0,
    @ColumnInfo var image: String? = ""
)