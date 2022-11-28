package com.javiermtz.composehome.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javiermtz.composehome.R


@Composable
fun ItemHome(name: String) {
  Box(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.border(BorderStroke(2.dp, Color.Blue))) {
      Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
      Text(
        text = name, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, fontSize = 20.sp)
    }

  }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewItem(){
  ItemHome(name = "Hola")
}

