package com.javiermtz.marvelapp.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.data.repository.RepositoryMarvel
import com.javiermtz.marvelapp.data.repository.ResultWrapper.GenericError
import com.javiermtz.marvelapp.data.repository.ResultWrapper.Loading
import com.javiermtz.marvelapp.data.repository.ResultWrapper.Success
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.domain.usecase.UseCases
import com.javiermtz.marvelapp.model.responses.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.userAgent
import javax.inject.Inject

@HiltViewModel
class CharacteresViewModel @Inject constructor(
  private val repositoryMarvel: RepositoryMarvel,
  private val useCases: UseCases
) : ViewModel() {

  private val _characters = MutableSharedFlow<List<CharactersMarvel>>()
  val characters = _characters.asSharedFlow()

  init {
    viewModelScope.launch {
      useCases.getMarvelCharactersUseCase().collectLatest {
        _characters.emit(it)
      }
    }

  }

  private val _characteresMarvel = MutableLiveData<MutableList<Results>>()
  val characteresMarvel: LiveData<MutableList<Results>> = _characteresMarvel

  private val _loading = MutableLiveData(true)
  val loading: LiveData<Boolean> = _loading

  private val _error = MutableLiveData("")
  val error: LiveData<String> = _error

}

