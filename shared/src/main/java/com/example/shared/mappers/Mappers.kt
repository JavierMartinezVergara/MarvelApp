package com.example.shared.mappers

import com.example.shared.models.CharacterDTO
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.shared.models.response.Results
import com.example.shared.models.response.ResultsComics
import com.example.shared.models.response.ResultsSeries

fun List<Results>.toListCharacters(): List<CharacterDTO> {
  val listCharacters: MutableList<CharacterDTO> = mutableListOf()
  for (heroes in this) {
    listCharacters.add(
      CharacterDTO(
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
        image = "${comic.thumbnail.path}.${comic.thumbnail.extension}",
        description = comic.description ?: "",
        urlLink = comic.urls.firstOrNull()?.url ?: "",
        price = comic.prices.firstOrNull()?.price ?: 0.0,
        writer = comic.creators.items.firstOrNull()?.name ?: "",
        datePublisher = comic.dates.firstOrNull {
          it.type == "onsaleDate"
        }?.date ?: "",
        creator = ""

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
