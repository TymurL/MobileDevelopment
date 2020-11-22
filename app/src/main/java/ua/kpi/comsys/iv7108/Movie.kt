package ua.kpi.comsys.iv7108

import org.json.JSONObject

class Movie : JSONObject {
    val title: String
    val year: String
    val rated: String
    val released: String
    val runtime: String
    val genre: String
    val director: String
    val writer: String
    val actors: String
    val plot: String
    val language: String
    val country: String
    val awards: String
    val imdbRating: String
    val imdbVotes: String
    val imdbID: String
    val type: String
    val poster: String
    val production: String

    constructor(json: String) : super(json) {
        title = this.getString("Title")
        year = this.getString("Year")
        rated = this.getString("Rated")
        released = this.getString("Released")
        runtime = this.getString("Runtime")
        genre = this.getString("Genre")
        director = this.getString("Director")
        writer = this.getString("Writer")
        actors = this.getString("Actors")
        plot = this.getString("Plot")
        language = this.getString("Language")
        country = this.getString("Country")
        awards = this.getString("Awards")
        imdbRating = this.getString("imdbRating")
        imdbVotes = this.getString("imdbVotes")
        imdbID = this.getString("imdbID")
        type = this.getString("Type")
        poster = this.getString("Poster")
        production = this.getString("Production")
    }

    constructor(movieTitle: String, movieType: String, movieYear: String) {
        title = movieTitle
        year = movieYear
        rated = ""
        released = ""
        runtime = ""
        genre = ""
        director = ""
        writer = ""
        actors = ""
        plot = ""
        language = ""
        country = ""
        awards = ""
        imdbRating = ""
        imdbVotes = ""
        imdbID = ""
        type = movieType
        poster = ""
        production = ""
    }
}

class JsonReader(json: String) : JSONObject(json) {
    val movies = this.getJSONArray("Search")
        .let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
        .map { Movie(it.toString()) }
}