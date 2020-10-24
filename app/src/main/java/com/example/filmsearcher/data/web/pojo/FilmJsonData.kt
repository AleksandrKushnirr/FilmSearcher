package com.example.filmsearcher.data.web.pojo

import com.google.gson.annotations.SerializedName

data class FilmJsonData (

	@SerializedName("data") val data : Data,
	@SerializedName("rating") val rating : Rating
)