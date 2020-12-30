package ua.kpi.comsys.iv7108

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 */
class MyItemRecyclerViewAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>(), Filterable {

    private val values: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.year.text = item.year
        holder.imdbID.text = item.imdbID
        holder.type.text = item.type
        Picasso.get().load(item.poster).into(holder.poster)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val title: TextView = view.findViewById(R.id.titleView)
        val year: TextView = view.findViewById(R.id.yearView)
        val imdbID: TextView = view.findViewById(R.id.imdbIDView)
        val type: TextView = view.findViewById(R.id.typeView)
        val poster: ImageView = view.findViewById(R.id.posterView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(values[position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    override fun getFilter(): Filter {
        return myFilter
    }

    private val myFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            if (!constraint.isNullOrBlank() && constraint.length >= 3) {
                loadMovies(constraint as String)
            }
            return FilterResults()
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        }
    }

    private fun loadMovies(search: String) {
        val queue = Volley.newRequestQueue(context)
        val url =
            "http://www.omdbapi.com/?apikey=7e9fe69e&s=${
                search.trim().replace(Regex("\\s+"), "+")
            }&page=1"
        val request = JsonObjectRequest(
            url,
            null,
            { response ->
                values.clear()
                try {
                    val movies = response.getJSONArray("Search")
                    for (i in 0 until movies.length()) {
                        val movie = movies.getJSONObject(i)
                        values.add(
                            Movie(
                                movie.getString("Title"),
                                movie.getString("Year"),
                                movie.getString("imdbID"),
                                movie.getString("Type"),
                                movie.getString("Poster")
                            )
                        )
                    }
                } catch (e: JSONException) {
                    values.clear()
                }
                notifyDataSetChanged()
            },
            { error ->
                error.printStackTrace()
                values.clear()
                notifyDataSetChanged()
            })
        queue.add(request)
    }

    fun addNewMovie(movie: Movie) {
        values.add(1, movie)
        notifyItemInserted(1)
    }

    fun deleteMovie(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }
}