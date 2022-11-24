package com.javiermtz.marvelapp.presentation.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
  private val usesCase : UseCases
) : ViewModel(){

  private val _comics = MutableStateFlow<List<ComicDTO>>(listOf())
  val comics = _comics.asStateFlow()

  private val _loading = MutableLiveData(true)
  val loading : LiveData<Boolean> = _loading

  private val _error = MutableLiveData("")
  val error : LiveData<String> = _error

  fun getComics(characterId : Int){
    viewModelScope.launch {
      usesCase.getMarvelComicsByCharacterUseCase(characterId).collectLatest {
        _comics.emit(it)
      }
    }
  }

}
