package com.darshan.roomcoroutine.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.data.local.entity.Restaurant
import com.darshan.roomcoroutine.data.repository.MainRepository
import com.darshan.roomcoroutine.utils.Resource
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val restaurants = MutableLiveData<Resource<List<Restaurant>>>()

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            restaurants.postValue(Resource.loading(null))
            try {
                val restaurantsFromDb = mainRepository.dbHelper.getRestaurants()
                if (restaurantsFromDb.isEmpty()) {
                    val restaurantsFromApi = mainRepository.apiHelper.getRestaurants()
                    val restaurantsToInsertInDB = mutableListOf<Restaurant>()
                    val restImagesToInsertInDB = mutableListOf<RestImage>()
                    for (apiRestaurant in restaurantsFromApi.data) {
                        apiRestaurant.img?.let {
                            restImagesToInsertInDB.addAll(it)
                        }
                        restaurantsToInsertInDB.add(apiRestaurant)
                    }
                    mainRepository.dbHelper.insertAllRestImages(restImagesToInsertInDB)
                    mainRepository.dbHelper.insertAll(restaurantsToInsertInDB)
                    restaurants.postValue(Resource.success(restaurantsToInsertInDB))
                } else {
                    restaurants.postValue(Resource.success(restaurantsFromDb))
                }
            } catch (e: Exception) {
                restaurants.postValue(Resource.error(null, "Something Went Wrong"))
            }
        }
    }

    fun getRestaurants(): LiveData<Resource<List<Restaurant>>> {
        return restaurants
    }

}