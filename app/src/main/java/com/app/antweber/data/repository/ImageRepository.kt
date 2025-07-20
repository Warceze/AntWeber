package com.app.antweber.data.repository

import com.app.antweber.data.api.UnsplashApi
import com.app.antweber.model.UnsplashResponse

class ImageRepository(private val api: UnsplashApi) {
    suspend fun getPhotos(
        page: Int = 1,
        perPage: Int = 16,
        orderBy: String = "relevant"
    ): List<UnsplashResponse> {
        return api.getPhotos(page = page, perPage = perPage, orderBy = orderBy)
    }
}