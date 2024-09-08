package com.app.antweber.data.api

import com.app.antweber.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplashApi {
    @GET("/photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String = "8hnetrrfIT1tqiP8JrWNwx_emMgBijLE_94pEfh9zao" ,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 16,
        @Query("order_by") orderBy: String = "latest"
    ): List<UnsplashResponse>

}