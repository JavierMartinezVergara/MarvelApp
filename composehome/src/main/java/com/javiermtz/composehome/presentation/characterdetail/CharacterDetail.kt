package com.javiermtz.composehome.presentation.characterdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.shared.models.ComicDTO

@Composable
fun CharacterDetail(navController: NavHostController, comicDTO: ComicDTO){
  Surface(modifier = Modifier.fillMaxSize()) {
    Column() {
      Text(text = comicDTO.description)

    }

  }
}


