package com.javiermtz.api.di

import com.javiermtz.api.remote.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelApiModule {

  @Singleton
  @Provides
  fun providesHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = BODY
    return OkHttpClient.Builder()
      .readTimeout(15, SECONDS)
      .addInterceptor(interceptor)
      .connectTimeout(15, SECONDS)
      .build()
  }

  @Singleton
  @Provides
  fun providesRetrofitMarvel(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://gateway.marvel.com/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun providesMarvelApi(retrofit: Retrofit): MarvelApi {
    return retrofit.create(MarvelApi::class.java)
  }
}
