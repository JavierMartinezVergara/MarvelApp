package com.javiermtz.composehome.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.shared.models.ComicDTO
import com.javiermtz.composehome.navigation.Screen.Home
import com.javiermtz.composehome.presentation.characterdetail.CharacterDetail
import com.javiermtz.composehome.presentation.home.HomeScreen
import com.javiermtz.composehome.utils.Utils.DETAILS_COMIC_KEY

@ExperimentalCoilApi
@ExperimentalLifecycleComposeApi
@Composable
fun SetupNavigationGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Home.route
  ) {
    composable(route = Home.route) {
      HomeScreen(navController = navController)
    }
    composable(
      route = Screen.ComicDetails.route,
    )
    {
      val comicDTO = navController.previousBackStackEntry?.savedStateHandle?.get<ComicDTO>(
        DETAILS_COMIC_KEY) ?: ComicDTO()
      CharacterDetail(navController = navController, comicDTO = comicDTO)
    }
    /*composable(
      route = Screen.Details.route,
      arguments = listOf(navArgument(
        DETAILS_ARGUMENT_KEY
      ) {
        type = NavType.IntType
      })
    ) {
      DetailScreen(navController = navController)
    }
    composable(route = Search.route) {
      SearchScreen(navController = navController)
    }*/

  }
}
