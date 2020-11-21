package ua.kpi.comsys.iv7108

import org.json.JSONObject

class Movie(json: String) : JSONObject(json) {
    val title: String = this.getString("Title")
    val year: String = this.getString("Year")
    val rated: String = this.getString("Rated")
    val released: String = this.getString("Released")
    val runtime: String = this.getString("Runtime")
    val genre: String = this.getString("Genre")
    val director: String = this.getString("Director")
    val writer: String = this.getString("Writer")
    val actors: String = this.getString("Actors")
    val plot: String = this.getString("Plot")
    val language: String = this.getString("Language")
    val country: String = this.getString("Country")
    val awards: String = this.getString("Awards")
    val imdbRating: String = this.getString("imdbRating")
    val imdbVotes: String = this.getString("imdbVotes")
    val imdbID: String = this.getString("imdbID")
    val type: String = this.getString("Type")
    val poster: String = this.getString("Poster")
    val production: String = this.getString("Production")
}

class JsonReader(json: String) : JSONObject(json) {
    val movies = this.getJSONArray("Search")
        .let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
        .map { Movie(it.toString()) }
}