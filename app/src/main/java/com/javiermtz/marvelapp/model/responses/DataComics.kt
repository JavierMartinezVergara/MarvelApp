package com.javiermtz.marvelapp.model.responses

import com.google.gson.annotations.SerializedName
import com.javiermtz.api.models.response.ResultsComics

data class DataComics(

  @SerializedName("offset") val offset: Int,
  @SerializedName("limit") val limit: Int,
  @SerializedName("total") val total: Int,
  @SerializedName("count") val count: Int,
  @SerializedName("results") val results: List<ResultsComics>
)
