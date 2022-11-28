package com.example.domain.di

import com.example.domain.usecase.GetMarvelCharactersUseCase
import com.example.domain.usecase.GetMarvelComicsByCharacterUseCase
import com.example.domain.usecase.GetMarvelComicsUseCase
import com.example.domain.usecase.GetMarvelSeriesUseCase
import com.example.domain.usecase.UseCases
import com.example.shared.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

  @Provides
  @Singleton
  fun providesUseCases(repository: MarvelRepository): UseCases {
    return UseCases(
      getMarvelCharactersUseCase = GetMarvelCharactersUseCase(repository = repository),
      getMarvelComicsUseCase = GetMarvelComicsUseCase(repository = repository),
      getMarvelSeriesUseCase = GetMarvelSeriesUseCase(repository),
      getMarvelComicsByCharacterUseCase = GetMarvelComicsByCharacterUseCase(repository)
    )
  }
}
