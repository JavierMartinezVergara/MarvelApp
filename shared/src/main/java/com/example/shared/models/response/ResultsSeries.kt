package com.example.shared.models.response

import com.google.gson.annotations.SerializedName

data class ResultsSeries(

  @SerializedName("id") val id: Int,
  @SerializedName("title") val title: String,
  @SerializedName("description") val description: String,
  @SerializedName("resourceURI") val resourceURI: String,
  @SerializedName("urls") val urls: List<Urls>,
  @SerializedName("startYear") val startYear: Int,
  @SerializedName("endYear") val endYear: Int,
  @SerializedName("rating") val rating: String,
  @SerializedName("modified") val modified: String,
  @SerializedName("thumbnail") val thumbnail: Thumbnail,
  @SerializedName("comics") val comics: Comics,
  @SerializedName("stories") val stories: StoryList,
  @SerializedName("events") val events: Events,
  @SerializedName("characters") val characters: CharactersList,
  @SerializedName("creators") val creators: CreatorsList,
  @SerializedName("next") val next: SeriesSummary,
  @SerializedName("previous") val previous: SeriesSummary,

  )
