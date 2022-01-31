package com.javiermtz.marvelapp.model.responses

import com.google.gson.annotations.SerializedName

data class Prices(

  @SerializedName("type") val type: String,
  @SerializedName("price") val price: Double
)
