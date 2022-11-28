package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.ComicDTO
import kotlinx.coroutines.flow.Flow

class GetMarvelComicsUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<List<ComicDTO>> {
    return repository.getComics()
  }
}
