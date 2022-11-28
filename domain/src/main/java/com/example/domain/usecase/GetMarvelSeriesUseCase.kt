package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.SerieDTO
import kotlinx.coroutines.flow.Flow

class GetMarvelSeriesUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<List<SerieDTO>> {
    return repository.getSeries()
  }
}
