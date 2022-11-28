package com.javiermtz.api.remote

import com.example.shared.models.CharactersMarvel
import com.example.shared.models.ComicDTO
import com.example.shared.models.SerieDTO
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {

  fun getCharacters() : Flow<List<CharactersMarvel>>

  fun getComics(): Flow<List<ComicDTO>>

  fun getComicsByCharacter(characterId: Int): Flow<List<ComicDTO>>

  fun getSeries(): Flow<List<SerieDTO>>
}
