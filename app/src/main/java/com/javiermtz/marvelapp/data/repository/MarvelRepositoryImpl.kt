package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.ComicDTO
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
}
