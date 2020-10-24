package com.example.filmsearcher.data.web.pojo

import com.google.gson.annotations.SerializedName


data class Data (
	@SerializedName("filmId") val filmId : Int,
	@SerializedName("nameRu") val nameRu : String,
	@SerializedName("nameEn") val nameEn : String,
	@SerializedName("webUrl") val webUrl : String,
	@SerializedName("posterUrl") val posterUrl : String,
	@SerializedName("year") val year : Int,
	@SerializedName("description") val description : String,
	@SerializedName("type") val type : String,
	@SerializedName("ratingAgeLimits") val ratingAgeLimits : Int,
	@SerializedName("countries") val countries : List<Countries>,
	@SerializedName("genres") val genres : List<Genres>
)