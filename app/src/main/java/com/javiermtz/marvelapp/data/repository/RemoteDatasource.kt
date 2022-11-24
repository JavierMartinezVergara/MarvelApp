package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.domain.models.SerieDTO
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {

  fun getCharacters() : Flow<List<CharactersMarvel>>

  fun getComics(): Flow<List<ComicDTO>>

  fun getComicsByCharacter(characterId: Int): Flow<List<ComicDTO>>

  fun getSeries(): Flow<List<SerieDTO>>
}
