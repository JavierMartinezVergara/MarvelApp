package com.javiermtz.marvelapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.data.repository.RepositoryMarvel
import com.javiermtz.marvelapp.data.repository.ResultWrapper.GenericError
import com.javiermtz.marvelapp.data.repository.ResultWrapper.Success
import com.javiermtz.marvelapp.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacteresViewModel @Inject constructor(
  private val repositoryMarvel: RepositoryMarvel
) : ViewModel(){


  private val _characteresMarvel = MutableLiveData<List<Results>>()
  val characteresMarvel : LiveData<List<Results>> = _characteresMarvel

  fun getData(){
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        val response = repositoryMarvel.getMarvelCharacters()
        when(response){
          is GenericError -> TODO()
          is Success -> {
            _characteresMarvel.postValue(response.dataResponse.data.results)
          }
        }
      }

    }
  }

}
