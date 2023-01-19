package com.example.dummyapicompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    var photoListResponse :List<Photos> by mutableStateOf(listOf())
    var errorMessage :String by mutableStateOf("")

    fun getPhotosList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {
                val photoList =apiService.getPhotos()
                photoListResponse = photoList
            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}