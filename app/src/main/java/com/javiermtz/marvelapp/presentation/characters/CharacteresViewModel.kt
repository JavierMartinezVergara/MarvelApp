package com.javiermtz.marvelapp.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.marvelapp.data.repository.RepositoryMarvel
import com.javiermtz.marvelapp.data.repository.ResultWrapper.GenericError
import com.javiermtz.marvelapp.data.repository.ResultWrapper.Loading
import com.javiermtz.marvelapp.data.repository.ResultWrapper.Success
import com.javiermtz.marvelapp.domain.usecase.UseCases
import com.javiermtz.marvelapp.model.responses.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacteresViewModel @Inject constructor(
  private val repositoryMarvel: RepositoryMarvel,
  private val useCases: UseCases
) : ViewModel(){

  val getCharacters = useCases.getMarvelCharactersUseCase()

  init {
    getCharacters()
  }



  private val _characteresMarvel = MutableLiveData<MutableList<Results>>()
  val characteresMarvel : LiveData<MutableList<Results>> = _characteresMarvel

  private val _loading = MutableLiveData(true)
  val loading : LiveData<Boolean> = _loading

  private val _error = MutableLiveData("")
  val error : LiveData<String> = _error

  var limite : Int = 0


  fun getCharacters(limit: Int = 0){
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        when(val response = repositoryMarvel.getMarvelCharacters(limit)){
          is GenericError -> {
            _loading.postValue(false)
            _error.postValue(response.error)
          }
          is Success -> {
            _error.postValue(null)
            _loading.postValue(false)
            val data = response.dataResponse.data.results.toMutableList()
            _characteresMarvel.postValue(data)
            limite = response.dataResponse.data.offset+100
          }
          Loading -> _loading.postValue(true)
        }
      }
    }
  }

  fun loading(){
    _loading.value = true
  }



}

operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
  val value = this.value ?: arrayListOf()
  value.addAll(values)
  this.value = value
}
