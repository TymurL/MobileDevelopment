package ua.kpi.comsys.iv7108

import org.json.JSONObject

class Movie(json: String) : JSONObject(json) {
    val title: String = this.getString("Title")
    val year: String = this.getString("Year")
    val imdbID: String = this.getString("imdbID")
    val type: String = this.getString("Type")
    val poster: String = this.getString("Poster")
}

class JsonReader(json: String) : JSONObject(json) {
    val movies = this.getJSONArray("Search")
        .let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
        .map { Movie(it.toString()) }
}