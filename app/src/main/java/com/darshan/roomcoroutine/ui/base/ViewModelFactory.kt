package com.darshan.roomcoroutine.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darshan.roomcoroutine.data.api.ApiHelper
import com.darshan.roomcoroutine.data.local.DatabaseHelper
import com.darshan.roomcoroutine.data.repository.MainRepository
import com.darshan.roomcoroutine.ui.detail.viewmodel.DetailViewModel
import com.darshan.roomcoroutine.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper, dbHelper)) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(MainRepository(apiHelper, dbHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

