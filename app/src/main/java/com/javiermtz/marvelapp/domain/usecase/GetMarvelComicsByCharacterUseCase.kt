package com.javiermtz.marvelapp.domain.usecase

import com.javiermtz.marvelapp.data.repository.MarvelRepository
import com.javiermtz.marvelapp.domain.models.ComicDTO
import kotlinx.coroutines.flow.Flow

class GetMarvelComicsByCharacterUseCase(private val repository: MarvelRepository) {

  operator fun invoke(characterId: Int): Flow<List<ComicDTO>> {
    return repository.getComicsByCharacter(characterId)
  }
}
