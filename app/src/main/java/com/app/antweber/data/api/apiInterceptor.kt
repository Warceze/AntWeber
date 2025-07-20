package com.app.antweber.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import android.util.Log

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            val response = chain.proceed(request)
            if (!response.isSuccessful) {
                Log.e("LoggingInterceptor", "Ошибка ответа: ${response.code}")
            }
            response
        } catch (e: IOException) {
            Log.e("LoggingInterceptor", "Ошибка сети: ${e.message}")
            throw e
        }
    }
}