package com.javiermtz.api.remote

import com.example.shared.MarvelRepository
import com.example.shared.models.CharacterDTO
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.shared.models.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
  private val remoteDataSource: RemoteDatasource
) : MarvelRepository {
  override fun getCharacters(): Flow<State<List<CharacterDTO>>> {
    return remoteDataSource.getCharacters()
  }

  override fun getComics(): Flow<State<List<ComicDTO>>> {
    return remoteDataSource.getComics()
  }

  override fun getSeries(): Flow<State<List<SerieDTO>>> {
    return remoteDataSource.getSeries()
  }

  override fun getComicsByCharacter(characterId: Int): Flow<State<List<ComicDTO>>> {
    return remoteDataSource.getComicsByCharacter(characterId)
  }
}
