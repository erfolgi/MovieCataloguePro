package com.erfolgi.moviecataloguepro.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvEntities")
data class TVEntity (

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String? = null,

//    @ColumnInfo(name = "genreIds")
//    var genreIds: List<Int?>? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

//    @ColumnInfo(name = "originCountry")
//    var originCountry: List<String?>? = null,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null,

    @ColumnInfo(name = "originalName")
    var originalName: String? = null,

    @ColumnInfo(name = "popularity")
    var popularity: Double? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var id: Int? = null,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Int? = null
)