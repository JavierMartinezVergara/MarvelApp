package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

  fun getCharacters() : Flow<List<CharactersMarvel>>
}
