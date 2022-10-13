package com.javiermtz.marvelapp.domain.models.mappers

import com.javiermtz.api.models.response.Results
import com.javiermtz.marvelapp.domain.models.CharactersMarvel

fun List<Results>.toListCharacters(): List<CharactersMarvel> {
  val listCharacters: MutableList<CharactersMarvel> = mutableListOf()
  for (heroes in this) {
    listCharacters.add(
      CharactersMarvel(id = heroes.id, name = heroes.name)
    )
  }
  return listCharacters.toList()
}
