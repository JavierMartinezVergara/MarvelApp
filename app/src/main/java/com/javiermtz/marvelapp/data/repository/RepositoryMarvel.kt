package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.data.remote.MarvelApi
import com.javiermtz.marvelapp.model.responses.ResponseMarvel
import com.javiermtz.marvelapp.model.responses.ResponseMarvelComics
import okio.IOException
import javax.inject.Inject

class RepositoryMarvel @Inject constructor(
  private val apiMarvel: MarvelApi,
  private val md5 : MD5
) {

  suspend fun getMarvelCharacters(
    offSet : Int = 0): ResultWrapper<ResponseMarvel> {
    return try {
      ResultWrapper.Loading
      val response = apiMarvel.getAllCharacteres(
        ts = md5.currentTimestamp,
        hash = md5.getMD5(),
        offset = offSet)
      if (response.isSuccessful) {
        ResultWrapper.Success(response.body()!!)
      } else {
        ResultWrapper.GenericError(response.message())
      }
    } catch (error: IOException) {
      ResultWrapper.GenericError(error.message)
    }

  }

  suspend fun getComics(charaterId : Int): ResultWrapper<ResponseMarvelComics> {
    return try {
      ResultWrapper.Loading
      val response = apiMarvel.getComics(characters = charaterId)
      if (response.isSuccessful) {
        ResultWrapper.Success(response.body()!!)
      } else {
        ResultWrapper.GenericError(response.message())
      }
    } catch (error: IOException) {
      ResultWrapper.GenericError(error.message)
    }

  }

}
