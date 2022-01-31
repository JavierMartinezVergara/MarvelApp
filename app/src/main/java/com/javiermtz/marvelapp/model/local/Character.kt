package com.javiermtz.marvelapp.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
  val id : Int,
  val name : String,
  val image : String,
  val description : String,
  val numComics : Int,
  val numSeries : Int,
): Parcelable
