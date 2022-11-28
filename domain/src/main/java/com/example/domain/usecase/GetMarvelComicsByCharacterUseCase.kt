package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.ComicDTO
import kotlinx.coroutines.flow.Flow

class GetMarvelComicsByCharacterUseCase(private val repository: MarvelRepository) {

  operator fun invoke(characterId: Int): Flow<List<ComicDTO>> {
    return repository.getComicsByCharacter(characterId)
  }
}
