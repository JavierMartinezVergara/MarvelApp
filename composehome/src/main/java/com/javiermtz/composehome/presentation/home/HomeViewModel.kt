package com.javiermtz.composehome.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.domain.usecase.UseCases
import com.example.shared.models.CharacterDTO
import com.example.shared.models.State.Empty
import com.example.shared.models.State.Failed
import com.example.shared.models.State.Loading
import com.example.shared.models.State.Success
import com.javiermtz.composehome.presentation.home.StateHome.Companion
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

  private val _comics = MutableStateFlow<StateHome<List<ComicDTO>>>(StateHome.loading())
  val comics = _comics.asStateFlow()

  private val _series = MutableStateFlow<StateHome<List<SerieDTO>>>(Companion.loading())
  val series = _series.asStateFlow()

  private val _characters =
    MutableStateFlow<StateHome<List<CharacterDTO>>>(StateHome.loading())
  val characters = _characters.asStateFlow()

  private val getCharacters = useCases.getMarvelCharactersUseCase()
  private val getComics = useCases.getMarvelComicsUseCase()
  private val getSeries = useCases.getMarvelSeriesUseCase()

  init {
    viewModelScope.launch {
      getCharacters.collectLatest { characters ->
        when (characters) {
          is Empty -> _characters.emit(StateHome.empty())
          is Failed -> _characters.emit(StateHome.failed(characters.message))
          is Loading -> _characters.emit(StateHome.loading())
          is Success -> _characters.emit(StateHome.success(characters.data))
        }
      }
      getSeries.collectLatest { series ->
        when (series) {
          is Empty -> _series.emit(StateHome.empty())
          is Failed -> _series.emit(StateHome.failed(series.message))
          is Loading -> _series.emit(StateHome.loading())
          is Success -> _series.emit(StateHome.success(series.data))
        }
      }
      getComics.collectLatest { comics ->
        when (comics) {
          is Empty -> _comics.emit(StateHome.empty())
          is Failed -> _comics.emit(StateHome.failed(comics.message))
          is Loading -> _comics.emit(StateHome.loading())
          is Success -> _comics.emit(StateHome.success(comics.data))
        }
      }
    }
  }

}

sealed class StateHome<T> {

  class Loading<T> : StateHome<T>()
  data class Success<T>(val data: T) : StateHome<T>()
  data class Failed<T>(val message: String) : StateHome<T>()
  class Empty<T> : StateHome<T>()

  companion object {
    fun <T> empty() = Empty<T>()
    fun <T> loading() = Loading<T>()
    fun <T> success(data: T) = Success(data)
    fun <T> failed(message: String) = Failed<T>(message)
  }
}


