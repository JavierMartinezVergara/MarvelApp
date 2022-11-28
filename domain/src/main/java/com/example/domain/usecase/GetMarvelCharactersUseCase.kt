package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.CharactersMarvel
import kotlinx.coroutines.flow.Flow

class GetMarvelCharactersUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<List<CharactersMarvel>> {
    return repository.getCharacters()
  }
}
