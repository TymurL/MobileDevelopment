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
        val imageView = findViewById<ImageView>(R.id.imageView).setImageResource(
            resources.getIdentifier(
                if (poster!!.isNotEmpty()) poster.substring(0, poster.lastIndexOf('.'))
                    .toLowerCase() else "",
                "drawable",
                this.packageName
            )
        )
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = intent.getStringExtra("Title")
        }
        val textView2 = findViewById<TextView>(R.id.textView2).apply {
            text = intent.getStringExtra("Year")
        }
        val textView3 = findViewById<TextView>(R.id.textView3).apply {
            text = intent.getStringExtra("imdbID")
        }
        val textView4 = findViewById<TextView>(R.id.textView4).apply {
            text = intent.getStringExtra("Rated")
        }
        val textView5 = findViewById<TextView>(R.id.textView5).apply {
            text = intent.getStringExtra("Released")
        }
        val textView6 = findViewById<TextView>(R.id.textView6).apply {
            text = intent.getStringExtra("Runtime")
        }
        val textView7 = findViewById<TextView>(R.id.textView7).apply {
            text = intent.getStringExtra("Genre")
        }
        val textView8 = findViewById<TextView>(R.id.textView8).apply {
            text = intent.getStringExtra("Director")
        }
        val textView9 = findViewById<TextView>(R.id.textView9).apply {
            text = intent.getStringExtra("Writer")
        }
        val textView10 = findViewById<TextView>(R.id.textView10).apply {
            text = intent.getStringExtra("Actors")
        }
        val textView11 = findViewById<TextView>(R.id.textView11).apply {
            text = intent.getStringExtra("Plot")
        }
        val textView12 = findViewById<TextView>(R.id.textView12).apply {
            text = intent.getStringExtra("Language")
        }
        val textView13 = findViewById<TextView>(R.id.textView13).apply {
            text = intent.getStringExtra("Country")
        }
        val textView14 = findViewById<TextView>(R.id.textView14).apply {
            text = intent.getStringExtra("Awards")
        }
        val textView15 = findViewById<TextView>(R.id.textView15).apply {
            text = intent.getStringExtra("imdbRating")
        }
        val textView16 = findViewById<TextView>(R.id.textView16).apply {
            text = intent.getStringExtra("imdbVotes")
        }
        val textView17 = findViewById<TextView>(R.id.textView17).apply {
            text = intent.getStringExtra("Production")
        }
    }
}