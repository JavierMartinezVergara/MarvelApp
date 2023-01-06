package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.CharacterDTO
import com.example.shared.models.SerieDTO
import com.example.shared.models.State
import kotlinx.coroutines.flow.Flow

class GetMarvelCharactersUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<State<List<CharacterDTO>>> {
    return repository.getCharacters()
  }
}
