package com.example.domain.usecase

import com.example.shared.MarvelRepository
import com.example.shared.models.SerieDTO
import com.example.shared.models.State
import kotlinx.coroutines.flow.Flow

class GetMarvelSeriesUseCase(private val repository: MarvelRepository) {

  operator fun invoke(): Flow<State<List<SerieDTO>>> {
    return repository.getSeries()
  }
}
