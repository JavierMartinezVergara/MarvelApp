package com.javiermtz.composehome.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.javiermtz.composehome.navigation.SetupNavigationGraph
import com.javiermtz.composehome.presentation.home.HomeScreen
import com.javiermtz.composehome.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalLifecycleComposeApi
@AndroidEntryPoint
class ComposeHome : ComponentActivity() {

  private lateinit var navController: NavHostController


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MarvelAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          navController = rememberNavController()
          SetupNavigationGraph(navController = navController)
        }
      }
    }
  }
}
