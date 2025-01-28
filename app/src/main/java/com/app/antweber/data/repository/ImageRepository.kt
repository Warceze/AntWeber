package com.app.antweber.data.repository

import com.app.antweber.data.api.UnsplashApi
import com.app.antweber.model.UnsplashResponse

class ImageRepository(private val api: UnsplashApi) {
    suspend fun getPhotos(clientId: String, orderBy: String): List<UnsplashResponse> {
        return api.getPhotos(clientId = clientId, orderBy = orderBy)
    }
}
