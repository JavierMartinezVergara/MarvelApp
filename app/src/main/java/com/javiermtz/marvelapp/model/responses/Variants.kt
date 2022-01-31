package com.javiermtz.marvelapp.model.responses

import com.google.gson.annotations.SerializedName

data class Variants(

  @SerializedName("resourceURI") val resourceURI: String,
  @SerializedName("name") val name: String
)
