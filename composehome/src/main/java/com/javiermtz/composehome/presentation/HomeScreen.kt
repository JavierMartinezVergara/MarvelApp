package com.javiermtz.composehome.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javiermtz.composehome.R


@Composable
fun HomeScreen() {
  Column(modifier = Modifier
    .fillMaxHeight()
    .padding(8.dp)) {
    TextHome(text = "Characters")
    TextHome(text = "Characters")


  }
}

@Preview(showSystemUi = true)
@Composable
fun PrevieHome(){
  HomeScreen()
}

@Composable
fun TextHome(text: String){
  Text(modifier = Modifier.padding(start = 8.dp), text = text, fontSize = 24.sp, fontStyle = MaterialTheme.typography.h1.fontStyle)
}

