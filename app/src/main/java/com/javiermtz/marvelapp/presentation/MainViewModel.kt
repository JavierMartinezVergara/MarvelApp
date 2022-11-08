package com.javiermtz.marvelapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

  private val _loader = MutableLiveData<Boolean>(true)
  val loader : LiveData<Boolean> = _loader

  init {
    viewModelScope.launch {
      delay(4000)
      _loader.value = false
    }
  }
}
