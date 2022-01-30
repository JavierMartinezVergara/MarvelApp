package com.javiermtz.marvelapp.data.remote

import com.javiermtz.marvelapp.BuildConfig
import com.javiermtz.marvelapp.model.ResponseMarvel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

  @GET("/v1/public/characters")
  suspend fun getAllCharacteres(
    @Query("apikey") apiKey : String =   BuildConfig.PUBLIC_API_KEY_MARVEL,
    @Query("ts") ts: Int = 1643528450,
    @Query("hash") hash: String = "f791190f97797b142ceccb5b02e6c561",

  ): Response<ResponseMarvel>

  @GET("search/photos")
  suspend fun searchImages(
    @Query("query") query: String,
    @Query("per_page") per_page: Int
  ): ResponseMarvel

}
