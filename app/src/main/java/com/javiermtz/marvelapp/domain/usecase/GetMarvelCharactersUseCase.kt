package com.javiermtz.marvelapp.domain.usecase

import com.javiermtz.marvelapp.data.repository.MarvelRepository
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import kotlinx.coroutines.flow.Flow

class GetMarvelCharactersUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<List<CharactersMarvel>> {
    return repository.getCharacters()
  }
}
