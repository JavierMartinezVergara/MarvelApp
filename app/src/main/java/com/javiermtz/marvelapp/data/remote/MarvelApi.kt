package com.javiermtz.marvelapp.data.remote

import com.javiermtz.marvelapp.BuildConfig
import com.javiermtz.marvelapp.model.responses.ResponseMarvel
import com.javiermtz.marvelapp.model.responses.ResponseMarvelComics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.Date

interface MarvelApi {

  @GET("/v1/public/characters")
  suspend fun getAllCharacteres(
    @Query("apikey") apiKey : String =   BuildConfig.PUBLIC_API_KEY_MARVEL,
    @Query("ts") ts: Int = 1643528450,
    @Query("hash") hash: String = "f791190f97797b142ceccb5b02e6c561",
    @Query("modifiedSince") modifiedSince : Long = SimpleDateFormat("dd-MM-yyyy").parse("01-0201-2017").time,
    @Query("offset") offset : Int = 200,
    @Query("limit") limit : Int = 100
  ): Response<ResponseMarvel>

  @GET("/v1/public/comics")
  suspend fun getComics(

    @Query("apikey") apiKey : String =   BuildConfig.PUBLIC_API_KEY_MARVEL,
    @Query("ts") ts: Int = 1643528450,
    @Query("hash") hash: String = "f791190f97797b142ceccb5b02e6c561",
    @Query("characters") characters : Int,
    @Query("limit") limit : Int = 100
  ): Response<ResponseMarvelComics>

}
