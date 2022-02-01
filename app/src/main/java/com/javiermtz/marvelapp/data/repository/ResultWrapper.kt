package com.javiermtz.marvelapp.data.repository


sealed class ResultWrapper<out T> {
  data class Success<out T>(val dataResponse: T): ResultWrapper<T>()
  data class GenericError(val error: String? = null): ResultWrapper<Nothing>()
  object Loading: ResultWrapper<Nothing>()
}
