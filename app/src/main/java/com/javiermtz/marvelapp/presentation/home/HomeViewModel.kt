package com.javiermtz.marvelapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.domain.models.ComicDTO
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

  val getCharacters = useCases.getMarvelCharactersUseCase()
  val getComics = useCases.getMarvelComicsUseCase()

  fun getComics(){
    viewModelScope.launch {
      getComics.collectLatest { comics ->
        _comics.emit(comics)
      }
    }
  }
}
