package com.javiermtz.api.models.response

import com.google.gson.annotations.SerializedName

data class Dates(

  @SerializedName("type") val type: String,
  @SerializedName("date") val date: String
)
