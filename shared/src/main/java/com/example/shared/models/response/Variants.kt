package com.example.shared.models.response

import com.google.gson.annotations.SerializedName

data class Variants(

  @SerializedName("resourceURI") val resourceURI: String,
  @SerializedName("name") val name: String
)
