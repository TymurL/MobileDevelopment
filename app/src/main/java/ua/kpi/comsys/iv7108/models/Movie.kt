package ua.kpi.comsys.iv7108.models

import java.io.Serializable

data class Movie(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
) : Serializable