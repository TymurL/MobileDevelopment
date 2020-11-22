package ua.kpi.comsys.iv7108

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class FilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        val poster = intent.getStringExtra("Poster")
        findViewById<ImageView>(R.id.imageView).setImageResource(
            resources.getIdentifier(
                if (poster!!.isNotEmpty()) poster.substring(0, poster.lastIndexOf('.'))
                    .toLowerCase() else "",
                "drawable",
                this.packageName
            )
        )
        findViewById<TextView>(R.id.textView).apply {
            text = intent.getStringExtra("Title")
        }
        findViewById<TextView>(R.id.textView2).apply {
            text = intent.getStringExtra("Year")
        }
        findViewById<TextView>(R.id.textView3).apply {
            text = intent.getStringExtra("imdbID")
        }
        findViewById<TextView>(R.id.textView4).apply {
            text = intent.getStringExtra("Rated")
        }
        findViewById<TextView>(R.id.textView5).apply {
            text = intent.getStringExtra("Released")
        }
        findViewById<TextView>(R.id.textView6).apply {
            text = intent.getStringExtra("Runtime")
        }
        findViewById<TextView>(R.id.textView7).apply {
            text = intent.getStringExtra("Genre")
        }
        findViewById<TextView>(R.id.textView8).apply {
            text = intent.getStringExtra("Director")
        }
        findViewById<TextView>(R.id.textView9).apply {
            text = intent.getStringExtra("Writer")
        }
        findViewById<TextView>(R.id.textView10).apply {
            text = intent.getStringExtra("Actors")
        }
        findViewById<TextView>(R.id.textView11).apply {
            text = intent.getStringExtra("Plot")
        }
        findViewById<TextView>(R.id.textView12).apply {
            text = intent.getStringExtra("Language")
        }
        findViewById<TextView>(R.id.textView13).apply {
            text = intent.getStringExtra("Country")
        }
        findViewById<TextView>(R.id.textView14).apply {
            text = intent.getStringExtra("Awards")
        }
        findViewById<TextView>(R.id.textView15).apply {
            text = intent.getStringExtra("imdbRating")
        }
        findViewById<TextView>(R.id.textView16).apply {
            text = intent.getStringExtra("imdbVotes")
        }
        findViewById<TextView>(R.id.textView17).apply {
            text = intent.getStringExtra("Production")
        }
    }
}