package com.app.antweber.data.api

import com.app.antweber.model.UnsplashResponse
import com.app.antweber.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/photos")
    suspend fun getPhotos(
        //Свой ключ от API Unsplash прописать в файле local.properties в поле UNSPLASH_API_KEY
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_API_KEY,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 16,
        @Query("order_by") orderBy: String = "latest"
    ): List<UnsplashResponse>
}