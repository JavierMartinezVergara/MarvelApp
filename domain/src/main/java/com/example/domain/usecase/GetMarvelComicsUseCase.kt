package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.ComicDTO
import com.example.shared.models.State
import kotlinx.coroutines.flow.Flow

class GetMarvelComicsUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<State<List<ComicDTO>>> {
    return repository.getComics()
  }
}
