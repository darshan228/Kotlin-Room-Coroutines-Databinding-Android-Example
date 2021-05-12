package com.darshan.roomcoroutine.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey var id: Int = 0,
    @ColumnInfo var title: String? = "",
    @ColumnInfo var phone_no: String? = "",
    @ColumnInfo var description: String? = "",
    @ColumnInfo var rating: Float? = 0f,
    @ColumnInfo var address: String? = "",
    @ColumnInfo var city: String? = "",
    @ColumnInfo var state: String? = "",
    @ColumnInfo var country: String? = "",
    @ColumnInfo var pincode: Int? = 0,
    @ColumnInfo var long: Double? = 0.00,
    @ColumnInfo var lat: Double? = 0.00,
    @Ignore var img: List<RestImage>? = null
)
