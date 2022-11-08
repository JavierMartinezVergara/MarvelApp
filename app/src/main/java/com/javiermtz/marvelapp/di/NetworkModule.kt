package com.javiermtz.marvelapp.di

import com.javiermtz.marvelapp.data.remote.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


  @Provides
  @Singleton
  fun provideMarvelApi(retrofit: Retrofit): MarvelApi {
    return retrofit.create(MarvelApi::class.java)
  }


}
