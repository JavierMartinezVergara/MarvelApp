package com.example.shared.models.response

import com.google.gson.annotations.SerializedName

data class CharactersList(

  @SerializedName("available") val available: Int,
  @SerializedName("collectionURI") val collectionURI: String,
  @SerializedName("items") val items: List<CharacterSummary>,
  @SerializedName("returned") val returned: Int
)
