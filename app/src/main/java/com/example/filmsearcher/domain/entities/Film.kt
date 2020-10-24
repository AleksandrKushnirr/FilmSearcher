package com.example.filmsearcher.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.filmsearcher.data.web.pojo.FilmJsonData

@Entity(indices = [Index("name")])
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "kinopoisk_id") var kinopoiskId: Int = 0,
    var name: String = "",
    @ColumnInfo(name = "name_ru") var nameRu: String = "",
    @ColumnInfo(name = "image_url") var imageUrl: String = "",
    var year: Int = 1999,
    @ColumnInfo(name = "age_limit") var ageLimit: Int = 5,
    var description: String = "",
    var countries: MutableList<String> = mutableListOf(),
    var genres: MutableList<String> = mutableListOf(),
    var rating: Double = 9.99,
    @ColumnInfo(name = "rating_imdb") var ratingImdb: Double = 9.99,
    @ColumnInfo(name = "is_liked") var isLiked: Boolean = false
) {
    constructor(filmJsonDataV2: FilmJsonData) : this() {
        this.kinopoiskId = filmJsonDataV2.data.filmId
        this.name = filmJsonDataV2.data.nameEn
        this.nameRu = filmJsonDataV2.data.nameRu
        this.imageUrl = filmJsonDataV2.data.posterUrl
        this.year = filmJsonDataV2.data.year
        this.ageLimit = filmJsonDataV2.data.ratingAgeLimits
        this.description = filmJsonDataV2.data.description
        for(country in filmJsonDataV2.data.countries) this.countries.add(country.country)
        for(genre in filmJsonDataV2.data.genres) this.genres.add(genre.genre)
        this.rating = filmJsonDataV2.rating.rating
        this.ratingImdb = filmJsonDataV2.rating.ratingImdb
    }
}
