package com.javiermtz.composehome.navigation

import com.example.shared.models.ComicDTO

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object ComicDetails : Screen(route = "details_screen/{comic}") {
        fun passComic(comic: ComicDTO): String = "details_screen/$comic"
    }
    object CharacterDetails : Screen(route = "details_screen/{character}") {
        fun passComic(character: ComicDTO): String = "details_screen/$character"
    }

    object Search : Screen(route = "search_screen")

}
