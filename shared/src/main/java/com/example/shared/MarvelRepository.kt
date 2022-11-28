package com.example.shared

import com.example.shared.models.CharactersMarvel
import com.example.shared.models.ComicDTO
import com.example.shared.models.SerieDTO
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

  fun getCharacters() : Flow<List<CharactersMarvel>>

  fun getComics() : Flow<List<ComicDTO>>

  fun getSeries() : Flow<List<SerieDTO>>

  fun getComicsByCharacter(characterId: Int): Flow<List<ComicDTO>>
}
