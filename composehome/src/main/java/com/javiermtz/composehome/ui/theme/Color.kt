package com.javiermtz.composehome.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)


//Examples of colors using MaterialTheme
val Colors.statusBarColor: Color
  @Composable
  get() = if (isLight) Purple500 else Color.Black

val Colors.welcomeScreenBackgroundColor
  @Composable
  get() = if (isLight) Color.White else Color.Black

