package com.javiermtz.marvelapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.domain.models.SerieDTO
import com.javiermtz.marvelapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val useCases: UseCases
) : ViewModel() {

  private val _comics = MutableSharedFlow<List<ComicDTO>>(replay = 2)
  val comics = _comics.asSharedFlow()

  private val _series = MutableSharedFlow<List<SerieDTO>>(replay = 2)
  val series = _series.asSharedFlow()

  private val _characters = MutableSharedFlow<List<CharactersMarvel>>(replay = 2)
  val characters = _characters.asSharedFlow()

  private val getCharacters = useCases.getMarvelCharactersUseCase()
  private val getComics = useCases.getMarvelComicsUseCase()
  private val getSeries = useCases.getMarvelSeriesUseCase()

  fun getComics() {
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
}
