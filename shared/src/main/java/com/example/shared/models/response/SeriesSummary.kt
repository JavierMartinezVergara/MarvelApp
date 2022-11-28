package com.example.shared.models.response

import com.google.gson.annotations.SerializedName

data class SeriesSummary(
    @SerializedName("name")val name: String,
    @SerializedName("resourceURI")val resourceURI: String
)
