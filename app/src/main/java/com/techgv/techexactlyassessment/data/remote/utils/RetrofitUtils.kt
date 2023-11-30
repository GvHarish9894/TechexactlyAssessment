package com.techgv.techexactlyassessment.data.remote.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Retrofit utilities
 */
object RetrofitUtils {


    fun getInterceptorClient(): OkHttpClient.Builder {

        // setting request timeout
        val okHttpClientBuilder = OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                chain.proceed(chain.request())
            }


        // this will log all the request details in logcat so only use it in debug builds
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)

        return okHttpClientBuilder
    }
}