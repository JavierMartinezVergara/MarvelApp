package com.javiermtz.marvelapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersMarvel(
  val id: Int,
  val name : String,
  val image : String,
  val description: String,
  val url: String
) : Parcelable
