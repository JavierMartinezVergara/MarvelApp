package com.javiermtz.api.remote

import com.example.shared.MarvelRepository
import com.example.shared.models.CharactersMarvel
import com.example.shared.models.ComicDTO
import com.example.shared.models.SerieDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
  private val remoteDataSource: RemoteDatasource
) : MarvelRepository {
  override fun getCharacters(): Flow<List<CharactersMarvel>> {
    return remoteDataSource.getCharacters()
  }

  override fun getComics(): Flow<List<ComicDTO>> {
    return remoteDataSource.getComics()
  }

  override fun getSeries(): Flow<List<SerieDTO>> {
    return remoteDataSource.getSeries()
  }

  override fun getComicsByCharacter(characterId: Int): Flow<List<ComicDTO>> {
    return remoteDataSource.getComicsByCharacter(characterId)
  }
}
