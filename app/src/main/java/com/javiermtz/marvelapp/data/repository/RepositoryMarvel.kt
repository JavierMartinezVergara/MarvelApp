package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.data.remote.MarvelApi
import com.javiermtz.marvelapp.model.ResponseMarvel
import com.javiermtz.marvelapp.model.Results
import okio.IOException
import javax.inject.Inject

class RepositoryMarvel @Inject constructor(
  private val apiMarvel: MarvelApi
) {

  suspend fun getMarvelCharacters(): ResultWrapper<ResponseMarvel> {
    return try {
      val response = apiMarvel.getAllCharacteres()
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
