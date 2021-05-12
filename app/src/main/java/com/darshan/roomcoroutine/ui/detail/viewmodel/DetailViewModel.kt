package com.darshan.roomcoroutine.ui.detail.viewmodel

import androidx.lifecycle.*
import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.data.local.entity.Restaurant
import com.darshan.roomcoroutine.data.repository.MainRepository
import com.darshan.roomcoroutine.utils.Resource
import kotlinx.coroutines.launch


class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val restaurant = MutableLiveData<Resource<Restaurant>>()
    val restImages = MutableLiveData<Resource<List<RestImage>>>()

    fun fetchRestaurantDetails(id: Int) {
        viewModelScope.launch {
            if (id == 0) {
                restaurant.postValue(Resource.error(null, "Invalid restaurant ID"))
                return@launch
            }
            restaurant.postValue(Resource.loading(null))
            try {
                val restaurantFromDb = mainRepository.dbHelper.getRestaurantDetail(id)
                restaurantFromDb?.let { restaurant.postValue(Resource.success(data = it)) }
            } catch (e: Exception) {
                e.stackTrace
                restaurant.postValue(Resource.error(null, "Something Went Wrong"))
            }
            try {
                val restImagesFromDb = mainRepository.dbHelper.getRestaurantImages(id)
                restImagesFromDb.let { restImages.postValue(Resource.success(data = it)) }
            } catch (e: Exception) {
                e.stackTrace
                restaurant.postValue(
                    Resource.error(
                        null, "Something Went Wrong while fetching images"
                    )
                )
            }
        }
    }

    fun getRestaurantDetails(): LiveData<Resource<Restaurant>> = restaurant

    fun getRestImages(): LiveData<Resource<List<RestImage>>> = restImages
}