package com.javiermtz.marvelapp.model.responses

import com.google.gson.annotations.SerializedName

data class Characters(

  @SerializedName("available") val available: Int,
  @SerializedName("collectionURI") val collectionURI: String,
  @SerializedName("items") val items: List<Items>,
  @SerializedName("returned") val returned: Int
)
