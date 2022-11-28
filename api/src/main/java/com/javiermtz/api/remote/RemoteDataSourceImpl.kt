package com.javiermtz.api.remote


import com.example.shared.mappers.toListCharacters
import com.example.shared.mappers.toListComics
import com.example.shared.mappers.toListSeries
import com.example.shared.models.CharactersMarvel
import com.example.shared.models.ComicDTO
import com.example.shared.models.SerieDTO
import com.javiermtz.api.util.MD5
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
  private val api: MarvelApi,
  private val mD5: MD5
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

  override fun getComics(): Flow<List<ComicDTO>> {
    var list: List<ComicDTO>
    return flow {
      val response = api.getComics()
      list = if (response.data.results.isNotEmpty()) {
        response.data.results.toListComics()
      } else {
        emptyList()
      }
      //TODO tienes un error en el mapper
      emit(list)
    }.catch { e ->
      print(e.message)
      emit(emptyList())
    }
      .flowOn(Dispatchers.IO)
  }

  override fun getComicsByCharacter(characterId: Int): Flow<List<ComicDTO>> {
    var list: List<ComicDTO>
    return flow {
      val response = api.getComicsByCharacter(
        characterId = characterId, ts = mD5.currentTimestamp,
        hash = mD5.getMD5()
      )
      list = if (response.data.results.isNotEmpty()) {
        response.data.results.toListComics()
      } else {
        emptyList()
      }
      //TODO tienes un error en el mapper
      emit(list)
    }.catch { e ->
      print(e.message)
      emit(emptyList())
    }
      .flowOn(Dispatchers.IO)
  }

  override fun getSeries(): Flow<List<SerieDTO>> {
    var list: List<SerieDTO>
    return flow {
      val response = api.getSeries()
      list = if (response.data.results.isNotEmpty()) {
        response.data.results.toListSeries().toList()
      } else {
        emptyList()
      }
      emit(list)
    }.catch { e ->
      print(e.message)
      emit(emptyList())
    }
      .flowOn(Dispatchers.IO)
  }
}
