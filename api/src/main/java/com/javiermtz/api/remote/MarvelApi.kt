package com.javiermtz.api.remote

import com.javiermtz.api.models.response.ResponseMarvel
import com.javiermtz.api.models.response.ResponseMarvelComics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat

interface MarvelApi {

  @GET("/v1/public/characters")
  suspend fun getAllCharacteres(
    @Query("apikey") apiKey: String = "cadf4a57a4c3262a0709cd0207f86d8f",
    @Query("ts") ts: Int = 1643528450,
    @Query("hash") hash: String = "f791190f97797b142ceccb5b02e6c561",
    @Query("modifiedSince") modifiedSince: Long = SimpleDateFormat("dd-MM-yyyy").parse("01-0201-2017").time,
    @Query("offset") offset: Int = 0,
    @Query("limit") limit: Int = 100
  ): ResponseMarvel

  @GET("/v1/public/comics")
  fun getComics(

    @Query("apikey") apiKey: String = "",
    @Query("ts") ts: Int = 1643528450,
    @Query("hash") hash: String = "f791190f97797b142ceccb5b02e6c561",
    @Query("characters") characters: Int,
    @Query("limit") limit: Int = 100
  ): ResponseMarvelComics
}
