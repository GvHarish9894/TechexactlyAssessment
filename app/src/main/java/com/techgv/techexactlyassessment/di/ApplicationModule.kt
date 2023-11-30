package com.techgv.techexactlyassessment.di

import com.techgv.techexactlyassessment.data.remote.utils.RemoteService
import com.techgv.techexactlyassessment.data.remote.utils.RetrofitUtils
import com.techgv.techexactlyassessment.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = "https://navkiraninfotech.com/g-mee-api/api/v1/"

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient.Builder {
        return RetrofitUtils.getInterceptorClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClientBuilder: OkHttpClient.Builder): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())

        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRepository(remoteService: RemoteService) =
        AppRepository(remoteService)

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)


}