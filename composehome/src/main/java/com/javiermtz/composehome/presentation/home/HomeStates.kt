package com.javiermtz.composehome.presentation.home

import com.example.shared.models.ComicDTO

sealed class HomeStates {
  object Loading : HomeStates()
  class Error(val message: String) : HomeStates()
  class Success(val data: List<ComicDTO>) : HomeStates()
}
