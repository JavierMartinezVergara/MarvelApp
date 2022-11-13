package com.javiermtz.marvelapp.di

import com.javiermtz.marvelapp.data.repository.MarvelRepository
import com.javiermtz.marvelapp.domain.usecase.GetMarvelCharactersUseCase
import com.javiermtz.marvelapp.domain.usecase.GetMarvelComicsUseCase
import com.javiermtz.marvelapp.domain.usecase.GetMarvelSeriesUseCase
import com.javiermtz.marvelapp.domain.usecase.UseCases
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
      getMarvelSeriesUseCase = GetMarvelSeriesUseCase(repository)
    )
  }
}
