package com.javiermtz.api.models.response

import com.google.gson.annotations.SerializedName

data class StorySummary(
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("type") val type: String
)
