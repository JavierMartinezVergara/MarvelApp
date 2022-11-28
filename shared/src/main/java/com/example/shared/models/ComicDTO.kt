package com.example.shared.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicDTO(
  val id: Int = 0,
  val title : String = "",
  val description : String = "",
  val image : String = "",
  val urlLink: String = "",
  val price: Double = 0.0,
  val creator: String = "Desconocido",
  val datePublisher: String = "",
  val writer : String = "Desconocido"
): Parcelable
