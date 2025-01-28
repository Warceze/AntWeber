package com.app.antweber.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.antweber.data.api.RetrofitInstance
import com.app.antweber.data.repository.ImageRepository
import com.app.antweber.model.UnsplashResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class HomeViewModel : ViewModel() {
    private val repository = ImageRepository(RetrofitInstance.api)
    private val _images = MutableStateFlow<List<UnsplashResponse>>(emptyList())
    private var unsplashKey = "UNSPLASH_API_KEY"
    val images: StateFlow<List<UnsplashResponse>> = _images.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        loadImages()
    }

    fun loadImages(orderBy: String = "latest") {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val response = repository.getPhotos(clientId = unsplashKey, orderBy = orderBy)
                _images.value = response
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Sorry, unable to load images", e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}