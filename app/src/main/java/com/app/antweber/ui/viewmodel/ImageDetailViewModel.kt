package com.app.antweber.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ImageState {
    object Loading : ImageState()
    data class Success(val url: String) : ImageState()
    object Error : ImageState()
}

class ImageDetailViewModel : ViewModel() {
    private val _imageState = MutableStateFlow<ImageState>(ImageState.Loading)
    val imageState: StateFlow<ImageState> = _imageState.asStateFlow()

    fun setImageUrl(url: String) {
        _imageState.value = ImageState.Loading

        viewModelScope.launch {
            delay(1100)
            if (url.isNotEmpty()) {
                Log.d("ImageDetailViewModel", "Setting image URL: $url")
                _imageState.value = ImageState.Success(url)
            } else {
                _imageState.value = ImageState.Error
            }
        }
    }
}