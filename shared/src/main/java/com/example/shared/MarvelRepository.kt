package com.example.shared

import com.example.shared.models.CharacterDTO
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.shared.models.State
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

  fun getCharacters() : Flow<State<List<CharacterDTO>>>

  fun getComics() : Flow<State<List<ComicDTO>>>

  fun getSeries() : Flow<State<List<SerieDTO>>>

  fun getComicsByCharacter(characterId: Int): Flow<State<List<ComicDTO>>>
}
