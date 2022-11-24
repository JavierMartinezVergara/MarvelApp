package com.javiermtz.marvelapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.domain.models.SerieDTO
import com.javiermtz.marvelapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val useCases: UseCases
) : ViewModel() {

  private val _comics = MutableStateFlow<List<ComicDTO>>(listOf())
  val comics = _comics.asStateFlow()

  private val _series = MutableStateFlow<List<SerieDTO>>(listOf())
  val series = _series.asStateFlow()

  private val _characters = MutableStateFlow<List<CharactersMarvel>>(listOf())
  val characters = _characters.asStateFlow()

  private val getCharacters = useCases.getMarvelCharactersUseCase()
  private val getComics = useCases.getMarvelComicsUseCase()
  private val getSeries = useCases.getMarvelSeriesUseCase()

  init {
    viewModelScope.launch {
      getCharacters.collectLatest { characters ->
        _characters.emit(characters)
      }
      getSeries.collectLatest { series ->
        _series.emit(series)
      }
      getComics.collectLatest { comics ->
        _comics.emit(comics)
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
  }
}

