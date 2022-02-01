package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.data.remote.MarvelApi
import com.javiermtz.marvelapp.model.responses.ResponseMarvel
import com.javiermtz.marvelapp.model.responses.ResponseMarvelComics
import okio.IOException
import javax.inject.Inject

class RepositoryMarvel @Inject constructor(
  private val apiMarvel: MarvelApi
) {

  suspend fun getMarvelCharacters(offSet : Int = 0): ResultWrapper<ResponseMarvel> {
    return try {
      ResultWrapper.Loading
      val response = apiMarvel.getAllCharacteres(offset = offSet)
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
