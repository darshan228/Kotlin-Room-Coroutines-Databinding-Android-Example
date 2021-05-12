package com.darshan.roomcoroutine.data.model

import com.darshan.roomcoroutine.data.local.entity.Restaurant

data class BaseResponse(
    val data: List<Restaurant>
)