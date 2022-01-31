package com.javiermtz.marvelapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.data.repository.RepositoryMarvel
import com.javiermtz.marvelapp.data.repository.ResultWrapper.GenericError
import com.javiermtz.marvelapp.data.repository.ResultWrapper.Success
import com.javiermtz.marvelapp.model.responses.Results
import com.javiermtz.marvelapp.model.responses.ResultsComics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacteresViewModel @Inject constructor(
  private val repositoryMarvel: RepositoryMarvel
) : ViewModel(){

  init {
    getCharacters()
  }


  private val _characteresMarvel = MutableLiveData<List<Results>>()
  val characteresMarvel : LiveData<List<Results>> = _characteresMarvel

  fun getCharacters(){
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        val response = repositoryMarvel.getMarvelCharacters()
        when(response){
          is GenericError -> response.error
          is Success -> {
            _characteresMarvel.postValue(response.dataResponse.data.results)
          }
        }
      }

    }
  }

}
