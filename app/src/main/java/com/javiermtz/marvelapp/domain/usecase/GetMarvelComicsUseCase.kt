package com.javiermtz.marvelapp.domain.usecase

import com.javiermtz.marvelapp.data.repository.MarvelRepository
import com.javiermtz.marvelapp.domain.models.ComicDTO
import kotlinx.coroutines.flow.Flow

class GetMarvelComicsUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<List<ComicDTO>> {
    return repository.getComics()
  }
}
