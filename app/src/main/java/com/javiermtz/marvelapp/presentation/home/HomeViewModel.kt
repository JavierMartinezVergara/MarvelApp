package com.javiermtz.marvelapp.presentation.home

import androidx.lifecycle.ViewModel
import com.example.shared.models.SerieDTO
import com.example.shared.models.ComicDTO
import com.example.domain.usecase.UseCases
import com.example.shared.models.State.Empty
import com.example.shared.models.State.Failed
import com.example.shared.models.State.Loading
import com.example.shared.models.State.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val useCases: UseCases
) : ViewModel() {

  private val _comics = MutableStateFlow<List<ComicDTO>>(listOf())
  val comics = _comics.asStateFlow()

  private val _series = MutableStateFlow<List<SerieDTO>>(listOf())
  val series = _series.asStateFlow()

  private val _characters =
    MutableStateFlow<StateHome<List<SerieDTO>>>(StateHome.empty())
  val characters = _characters.asStateFlow()

  private val getCharacters = useCases.getMarvelCharactersUseCase()
  private val getComics = useCases.getMarvelComicsUseCase()
  private val getSeries = useCases.getMarvelSeriesUseCase()

  init {
    viewModelScope.launch {
      getCharacters.collectLatest { characters ->
        when (characters) {
          is Empty -> _characters.emit(StateHome.empty())

          is Failed -> TODO()
          is Loading -> TODO()
          is Success -> TODO()
        }
      }
      getSeries.collectLatest { series ->
        when (series) {
          is Empty -> TODO()
          is Failed -> TODO()
          is Loading -> TODO()
          is Success -> TODO()
        }
      }
      getComics.collectLatest { comics ->
        when (comics) {
          is Empty -> TODO()
          is Failed -> TODO()
          is Loading -> TODO()
          is Success -> TODO()
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

