package com.javiermtz.composehome.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.shared.models.ComicDTO
import com.javiermtz.composehome.R
import com.javiermtz.composehome.navigation.Screen
import com.javiermtz.composehome.ui.theme.ITEM_HEIGHT
import com.javiermtz.composehome.ui.theme.ITEM_WIDTH
import com.javiermtz.composehome.ui.theme.SMALL_PADDING
import com.javiermtz.composehome.utils.Utils.DETAILS_COMIC_KEY

@ExperimentalCoilApi
@Composable
fun ListContentComics(
  comics: List<ComicDTO>,
  navController: NavHostController
) {
  LazyRow() {
    items(comics) { comic ->
      ComicItem(comic = comic) { comicData ->
        navController.currentBackStackEntry?.savedStateHandle?.set<ComicDTO>(
          DETAILS_COMIC_KEY, comicData
        )
        navController.navigate(Screen.ComicDetails.route)
      }
    }

  }

}

@ExperimentalCoilApi
@Composable
fun ComicItem(comic: ComicDTO, comicListener: (ComicDTO) -> Unit) {
  Card(
    shape = RoundedCornerShape(4.dp),
    border = BorderStroke(width = 2.dp, color = Color.DarkGray),
    elevation = SMALL_PADDING,
    modifier = Modifier
      .padding(SMALL_PADDING)
      .width(ITEM_WIDTH)
      .height(ITEM_HEIGHT)
      .clickable {
        comicListener(comic)
      }
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {

      val painter = rememberImagePainter(data = comic.image) {
        placeholder(R.drawable.splash_image)
        error(R.drawable.marvel_ic)
      }
      if (comic.title.isNotEmpty()) {
        Image(
          painter = painter,
          contentDescription = "",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
        )
        Text(
          text = comic.title,
          style = MaterialTheme.typography.body2,
          textAlign = TextAlign.Center,
          maxLines = 2,
          modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp)
        )
      } else {
        Image(
          painter = painter,
          contentDescription = "",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxSize()

        )

      }

    }

  }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun Preview() {
  ComicItem(comic = ComicDTO(description = "Wolwerine")) {

  }
}

