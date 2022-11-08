package com.javiermtz.marvelapp.domain.models.mappers

import com.javiermtz.api.models.response.Results
import com.javiermtz.api.models.response.ResultsComics
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.ComicDTO

fun List<Results>.toListCharacters(): List<CharactersMarvel> {
  val listCharacters: MutableList<CharactersMarvel> = mutableListOf()
  for (heroes in this) {
    listCharacters.add(
      CharactersMarvel(
        id = heroes.id,
        name = heroes.name,
        image = "${heroes.thumbnail.path}.${heroes.thumbnail.extension}"
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
