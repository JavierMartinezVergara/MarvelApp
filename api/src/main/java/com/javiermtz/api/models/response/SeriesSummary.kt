package com.javiermtz.api.models.response

import com.google.gson.annotations.SerializedName

data class SeriesSummary(
    @SerializedName("name")val name: String,
    @SerializedName("resourceURI")val resourceURI: String
)
