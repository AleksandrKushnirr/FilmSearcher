package com.example.filmsearcher.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Filter(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    var genres: MutableList<String> = mutableListOf<String>("Аниме", "Боевик", "Биография", "Вестерн", "Военный", "Детектив", "Документальный",
                                                    "Драма", "История", "Комедия", "Криминал", "Мелодрама", "Музыка", "Мультфильм", "Мюзикл",
                                                    "Приключения", "Семейный", "Спорт", "Триллер", "Ужасы", "Фантастика", "Фильм-нуар", "Фэнтези"),
    val countries: MutableList<String> = mutableListOf<String>("США", "Россия", "СССР", "Франция", "Италия", "Испания", "Великобритания",
                                                        "Германия", "Япония", "Украина", "Другие"),
    @ColumnInfo(name = "min_year") var minYear: Int = 1960,
    @ColumnInfo(name = "max_year") var maxYear: Int = 2015,
    @ColumnInfo(name = "min_rating_kino") var minRatingKino: Double = 5.0,
    @ColumnInfo(name = "max_rating_kino") var maxRatingKino: Double = 9.5,
    @ColumnInfo(name = "min_rating_imdb") var minRatingImdb: Double = 5.0,
    @ColumnInfo(name = "max_rating_imdb") var maxRatingImdb: Double = 9.5,
    @ColumnInfo(name = "age_limit") val ageLimit: MutableList<String> = mutableListOf("0+", "6+", "12+", "16+")
)