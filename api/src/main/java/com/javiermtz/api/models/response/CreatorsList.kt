package com.javiermtz.api.models.response

import com.google.gson.annotations.SerializedName

data class CreatorsList(

  @SerializedName("available") val available: Int,
  @SerializedName("collectionURI") val collectionURI: String,
  @SerializedName("items") val items: List<CharacterSummary>,
  @SerializedName("returned") val returned: Int
)
