package ua.kpi.comsys.iv7108

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class FilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        val detailedMovie = intent.getParcelableExtra<DetailedMovie>("movie")

        if (detailedMovie != null) {
            Picasso.get().load(detailedMovie.movie.poster)
                .into(findViewById<ImageView>(R.id.imageView))
            findViewById<TextView>(R.id.textView).text = detailedMovie.movie.title
            findViewById<TextView>(R.id.textView2).text = detailedMovie.movie.year
            findViewById<TextView>(R.id.textView3).text = detailedMovie.movie.imdbID
            findViewById<TextView>(R.id.textView4).text = detailedMovie.rated
            findViewById<TextView>(R.id.textView5).text = detailedMovie.released
            findViewById<TextView>(R.id.textView6).text = detailedMovie.runtime
            findViewById<TextView>(R.id.textView7).text = detailedMovie.genre
            findViewById<TextView>(R.id.textView8).text = detailedMovie.director
            findViewById<TextView>(R.id.textView9).text = detailedMovie.writer
            findViewById<TextView>(R.id.textView10).text = detailedMovie.actors
            findViewById<TextView>(R.id.textView11).text = detailedMovie.plot
            findViewById<TextView>(R.id.textView12).text = detailedMovie.language
            findViewById<TextView>(R.id.textView13).text = detailedMovie.country
            findViewById<TextView>(R.id.textView14).text = detailedMovie.awards
            findViewById<TextView>(R.id.textView15).text = detailedMovie.imdbRating
            findViewById<TextView>(R.id.textView16).text = detailedMovie.imdbVotes
            findViewById<TextView>(R.id.textView17).text = detailedMovie.production
        }
    }
}