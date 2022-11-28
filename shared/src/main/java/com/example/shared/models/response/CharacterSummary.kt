package com.example.shared.models.response

import com.google.gson.annotations.SerializedName

data class CharacterSummary(
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("role") val role: String
)
