package com.example.filmsearcher.data.web.pojo

import com.google.gson.annotations.SerializedName

data class Rating (
	@SerializedName("rating") val rating : Double,
	@SerializedName("ratingImdb") val ratingImdb: Double
)