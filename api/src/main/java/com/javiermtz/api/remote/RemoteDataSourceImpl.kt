package com.javiermtz.api.remote

import com.example.shared.mappers.toListCharacters
import com.example.shared.mappers.toListComics
import com.example.shared.mappers.toListSeries
import com.example.shared.models.CharacterDTO
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.shared.models.State
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
  override fun getCharacters(): Flow<State<List<CharacterDTO>>> {
    return flow {
      emit(State.loading())
      val response = api.getAllCharacteres()
      if (response.data.results.isNotEmpty()) {
        val data = response.data.results.toListCharacters()
        emit(State.success(data))
      } else {
        emit(State.empty())
      }
    }
      .catch { e ->
        print(e.message)
        emit(State.failed(e.message.toString()))
      }
      .flowOn(Dispatchers.IO)

  }

  override fun getComics(): Flow<State<List<ComicDTO>>> {
    return flow {
      emit(State.loading())
      val response = api.getComics()
      if (response.data.results.isNotEmpty()) {
        val data = response.data.results.toListComics()
        emit(State.success(data))
      } else {
        emit(State.empty())
      }
    }.catch { e ->
      print(e.message)
      emit(State.failed(e.message.toString()))
    }
      .flowOn(Dispatchers.IO)
  }

  override fun getComicsByCharacter(characterId: Int): Flow<State<List<ComicDTO>>> {
    return flow {
      emit(State.loading())
      val response = api.getComicsByCharacter(
        characterId = characterId, ts = mD5.currentTimestamp,
        hash = mD5.getMD5()
      )
      if (response.data.results.isNotEmpty()) {
        val data = response.data.results.toListComics()
        emit(State.success(data))
      } else {
        emit(State.empty())
      }
    }.catch { e ->
      print(e.message)
      emit(State.failed(e.message.toString()))
    }
      .flowOn(Dispatchers.IO)
  }

  override fun getSeries(): Flow<State<List<SerieDTO>>> {
    return flow {
      emit(State.loading())
      val response = api.getSeries()
      if (response.data.results.isNotEmpty()) {
        val data = response.data.results.toListSeries().toList()
        emit(State.success(data))
      } else {
        emit(State.empty())
      }
    }.catch { e ->
      print(e.message)
      emit(State.failed(e.message.toString()))
    }
      .flowOn(Dispatchers.IO)
  }
}
