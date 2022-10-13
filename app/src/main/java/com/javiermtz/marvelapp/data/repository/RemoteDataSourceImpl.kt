package com.javiermtz.marvelapp.data.repository

import com.javiermtz.api.*
import com.javiermtz.api.remote.MarvelApi
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.mappers.toListCharacters
import com.javiermtz.marvelapp.model.responses.ResponseMarvel
import com.javiermtz.marvelapp.model.responses.ResponseMarvelComics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
  private val api: MarvelApi
) : RemoteDatasource {
  override fun getCharacters(): Flow<List<CharactersMarvel>> {
    var list: List<CharactersMarvel>
    return flow {
      val response = api.getAllCharacteres()
      list = if (response.data.results.isNotEmpty()) {
        response.data.results.toListCharacters().toMutableList()
      } else {
        emptyList()
      }
      emit(list)
    }
      .catch { e ->
        print(e.message)
        emit(emptyList())
      }
      .flowOn(Dispatchers.IO)

  }

  override fun getComics(): Flow<ResponseMarvelComics> {
    TODO("Not yet implemented")
  }
}
