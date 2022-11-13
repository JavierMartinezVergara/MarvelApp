package com.javiermtz.marvelapp.domain.models.mappers

import com.javiermtz.api.models.response.Results
import com.javiermtz.api.models.response.ResultsComics
import com.javiermtz.api.models.response.ResultsSeries
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.domain.models.SerieDTO

fun List<Results>.toListCharacters(): List<CharactersMarvel> {
  val listCharacters: MutableList<CharactersMarvel> = mutableListOf()
  for (heroes in this) {
    listCharacters.add(
      CharactersMarvel(
        id = heroes.id,
        name = heroes.name,
        image = "${heroes.thumbnail.path}.${heroes.thumbnail.extension}",
        description = heroes.description,
        url = heroes.urls.firstOrNull()?.type ?: ""
      )
    )
  }
  return listCharacters.toList()
}

fun List<ResultsComics>.toListComics(): List<ComicDTO> {
  val listComics: MutableList<ComicDTO> = mutableListOf()
  for (comic in this) {
    listComics.add(
      ComicDTO(
        id = comic.id,
        title = comic.title,
        image = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
      )
    )
  }
  return listComics.toList()
}

fun List<ResultsSeries>.toListSeries(): List<SerieDTO> {
  val listSeries: MutableList<SerieDTO> = mutableListOf()
  for (comic in this) {
    listSeries.add(
      SerieDTO(
        id = comic.id,
        title = comic.title,
        image = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
      )
    )
  }
  return listSeries.toList()
}
