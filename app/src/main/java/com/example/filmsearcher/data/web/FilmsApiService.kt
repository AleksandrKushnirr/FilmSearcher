package com.example.filmsearcher.data.web

import com.example.filmsearcher.data.web.pojo.FilmJsonData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApiService {
    @GET("/api/v2.1/films/{id}?append_to_response=RATING")
    fun getFilmsWithRating(@Path("id") int: Int): Deferred<FilmJsonData>
}