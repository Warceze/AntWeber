package com.app.antweber.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import coil.ImageLoader
import com.app.antweber.BuildConfig
import com.app.antweber.data.api.RetrofitInstance
import com.app.antweber.data.repository.ImageRepository
import com.app.antweber.model.UnsplashResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = ImageRepository(RetrofitInstance.api)
    private val _images = MutableStateFlow<List<UnsplashResponse>>(emptyList())
    val images: StateFlow<List<UnsplashResponse>> = _images.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private var currentPage = 1

    init {
        Log.d("HomeViewModel", "API_KEY: ${BuildConfig.UNSPLASH_API_KEY}")
        loadImages()
    }

    fun loadImages(orderBy: String = "Relevant") {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val response = repository.getPhotos()
                _images.value = response
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Ошибка при загрузке изображений", e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun loadMoreImages() {
        currentPage++
        loadImages()
    }
}