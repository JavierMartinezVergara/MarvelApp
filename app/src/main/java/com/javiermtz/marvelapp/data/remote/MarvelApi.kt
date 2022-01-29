package com.javiermtz.marvelapp.data.remote

import com.javiermtz.marvelapp.BuildConfig
import com.javiermtz.marvelapp.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

  @GET("/v1/public/characters")
  suspend fun getAllImages(
    @Query("apikey") apiKey : String = "${BuildConfig.PUBLIC_API_KEY_MARVEL}",
    @Query("per_page") per_page: Int
  ): List<Data>

  @GET("search/photos")
  suspend fun searchImages(
    @Query("query") query: String,
    @Query("per_page") per_page: Int
  ): Data

}
