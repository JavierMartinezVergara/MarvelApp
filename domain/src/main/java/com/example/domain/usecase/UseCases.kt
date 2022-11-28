package com.example.domain.usecase

data class UseCases(
  val getMarvelCharactersUseCase: GetMarvelCharactersUseCase,
  val getMarvelComicsUseCase: GetMarvelComicsUseCase,
  val getMarvelSeriesUseCase: GetMarvelSeriesUseCase,
  val getMarvelComicsByCharacterUseCase: GetMarvelComicsByCharacterUseCase
)
