package com.javiermtz.marvelapp.data.repository

import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.model.responses.ResponseMarvel
import com.javiermtz.marvelapp.model.responses.ResponseMarvelComics
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {

  fun getCharacters() : Flow<List<CharactersMarvel>>

  fun getComics(): Flow<ResponseMarvelComics>
}
