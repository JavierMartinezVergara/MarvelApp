package com.example.shared.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDTO(
  val id: Int,
  val name : String,
  val image : String,
  val description: String,
  val url: String
) : Parcelable
