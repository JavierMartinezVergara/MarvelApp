package com.javiermtz.marvelapp.domain.usecase

import com.javiermtz.marvelapp.data.repository.MarvelRepository
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.domain.models.SerieDTO
import kotlinx.coroutines.flow.Flow

class GetMarvelSeriesUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<List<SerieDTO>> {
    return repository.getSeries()
  }
}
