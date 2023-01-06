package com.javiermtz.api.remote

import com.example.shared.models.CharacterDTO
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.shared.models.State
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {

  fun getCharacters() : Flow<State<List<CharacterDTO>>>

  fun getComics(): Flow<State<List<ComicDTO>>>

  fun getComicsByCharacter(characterId: Int): Flow<State<List<ComicDTO>>>

  fun getSeries(): Flow<State<List<SerieDTO>>>
}
