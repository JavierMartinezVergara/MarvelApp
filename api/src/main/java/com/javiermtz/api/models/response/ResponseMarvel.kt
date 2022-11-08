package com.javiermtz.api.models.response

import com.google.gson.annotations.SerializedName

data class ResponseMarvel(

  @SerializedName("code") val code: Int,
  @SerializedName("status") val status: String,
  @SerializedName("copyright") val copyright: String,
  @SerializedName("attributionText") val attributionText: String,
  @SerializedName("attributionHTML") val attributionHTML: String,
  @SerializedName("etag") val etag: String,
  @SerializedName("data") val data: Data
)
