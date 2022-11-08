package com.javiermtz.api.models.response

import com.google.gson.annotations.SerializedName

data class Prices(

  @SerializedName("type") val type: String,
  @SerializedName("price") val price: Double
)
