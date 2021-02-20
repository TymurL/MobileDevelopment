package ua.kpi.comsys.iv7108.models

import android.os.Parcel
import android.os.Parcelable

data class DetailedMovie(
    val movie: Movie,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val imdbRating: String,
    val imdbVotes: String,
    val production: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable<Movie>(Movie::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(movie, flags)
        parcel.writeString(rated)
        parcel.writeString(released)
        parcel.writeString(runtime)
        parcel.writeString(genre)
        parcel.writeString(director)
        parcel.writeString(writer)
        parcel.writeString(actors)
        parcel.writeString(plot)
        parcel.writeString(language)
        parcel.writeString(country)
        parcel.writeString(awards)
        parcel.writeString(imdbRating)
        parcel.writeString(imdbVotes)
        parcel.writeString(production)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailedMovie> {
        override fun createFromParcel(parcel: Parcel): DetailedMovie {
            return DetailedMovie(parcel)
        }

        override fun newArray(size: Int): Array<DetailedMovie?> {
            return arrayOfNulls(size)
        }
    }
}