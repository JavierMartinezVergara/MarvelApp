package com.javiermtz.composehome.presentation.home

import android.util.Log
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.javiermtz.composehome.R
import com.javiermtz.composehome.presentation.components.ShimmerEffect
import com.javiermtz.composehome.presentation.home.StateHome.Empty
import com.javiermtz.composehome.presentation.home.StateHome.Failed
import com.javiermtz.composehome.presentation.home.StateHome.Loading
import com.javiermtz.composehome.presentation.home.StateHome.Success

@ExperimentalCoilApi
@ExperimentalLifecycleComposeApi
@Composable
fun HomeScreen(
  viewModel: HomeViewModel = hiltViewModel(),
  navController: NavHostController
) {

  val lifeCyclerOwner = LocalLifecycleOwner.current
  val flow = remember(viewModel.comics, lifeCyclerOwner) {
    viewModel.comics.flowWithLifecycle(lifeCyclerOwner.lifecycle, Lifecycle.State.RESUMED)
  }.collectAsStateWithLifecycle(StateHome.loading())

  val characterFlow = remember(viewModel.comics, lifeCyclerOwner) {
    viewModel.characters.flowWithLifecycle(lifeCyclerOwner.lifecycle, Lifecycle.State.RESUMED)
  }.collectAsStateWithLifecycle(StateHome.loading())

  val seriesFlow = remember(viewModel.comics, lifeCyclerOwner) {
    viewModel.series.flowWithLifecycle(lifeCyclerOwner.lifecycle, Lifecycle.State.RESUMED)
  }.collectAsStateWithLifecycle(StateHome.loading())


  Column(
    modifier = Modifier
      .fillMaxHeight()
      .verticalScroll(rememberScrollState())
      .padding(8.dp)
  ) {
    TextHome(text = stringResource(id = R.string.comics ))
    when (val state = flow.value) {
      is Empty -> Log.d("messahe", "Loa")
      is Failed -> Log.d("messahe", "Loa")
      is Loading -> ShimmerEffect()
      is Success -> ListContentComics(comics = state.data, navController = navController)
    }

    TextHome(text = stringResource(id = R.string.characters))

    when (val state = characterFlow.value) {
      is Empty -> Log.d("messahe", "Loa")
      is Failed -> Log.d("messahe", "Loa")
      is Loading -> ShimmerEffect()
      is Success -> ListContentCharacters(characters = state.data, navController = navController)
    }

    TextHome(text = stringResource(id = R.string.series))

    when (val state = seriesFlow.value) {
      is Empty -> Log.d("messahe", "Loa")
      is Failed -> Log.d("messahe", "Loa")
      is Loading -> ShimmerEffect()
      is Success -> ListContentSeries(series = state.data, navController = navController)
    }

  }
}

@Preview(showSystemUi = true)
@Composable
fun PrevieHome() {

}

@Composable
fun TextHome(text: String) {
  Text(
    modifier = Modifier.padding(start = 8.dp),
    text = text,
    fontSize = 24.sp,
    fontStyle = MaterialTheme.typography.h1.fontStyle
  )
}

