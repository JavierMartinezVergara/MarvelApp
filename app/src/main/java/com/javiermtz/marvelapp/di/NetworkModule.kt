package com.javiermtz.marvelapp.di

import com.javiermtz.marvelapp.data.remote.MarvelApi
import com.javiermtz.marvelapp.BuildConfig
import com.javiermtz.marvelapp.util.Constans.MARVELAPI_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .readTimeout(15, TimeUnit.SECONDS)
      .connectTimeout(15, TimeUnit.SECONDS)
      .addInterceptor(HttpLoggingInterceptor().apply {
        level =
          if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
      })
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
      .baseUrl(MARVELAPI_URL)
      .client(okHttpClient)
      .build()
  }

  @Provides
  @Singleton
  fun provideMarvelApi(retrofit: Retrofit): MarvelApi {
    return retrofit.create(MarvelApi::class.java)
  }

}
