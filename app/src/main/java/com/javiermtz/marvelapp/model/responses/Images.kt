package com.javiermtz.marvelapp.model.responses

import com.google.gson.annotations.SerializedName

data class Images(

  @SerializedName("path") val path: String,
  @SerializedName("extension") val extension: String
)
