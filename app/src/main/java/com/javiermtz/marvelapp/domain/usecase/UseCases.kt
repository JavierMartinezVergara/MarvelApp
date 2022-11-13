package com.javiermtz.marvelapp.domain.usecase

data class UseCases(
  val getMarvelCharactersUseCase: GetMarvelCharactersUseCase,
  val getMarvelComicsUseCase: GetMarvelComicsUseCase,
  val getMarvelSeriesUseCase: GetMarvelSeriesUseCase
)
