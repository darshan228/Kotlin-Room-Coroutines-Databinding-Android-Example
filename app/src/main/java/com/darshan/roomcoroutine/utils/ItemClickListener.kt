package com.darshan.roomcoroutine.utils

import com.darshan.roomcoroutine.data.local.entity.Restaurant

interface ItemClickListener {
    fun onListItemClicked(restaurant: Restaurant)
}