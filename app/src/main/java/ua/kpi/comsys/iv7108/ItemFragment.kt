package ua.kpi.comsys.iv7108

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.io.InputStream

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment(), MyItemRecyclerViewAdapter.OnItemClickListener {

    private var columnCount = 1
    private lateinit var movies: MutableList<Movie>
    private lateinit var myAdapter: MyItemRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                movies = JsonReader(loadJSONFromAsset()).movies as MutableList<Movie>
                myAdapter = MyItemRecyclerViewAdapter(movies, this@ItemFragment)
                adapter = myAdapter
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)

        val searchView: SearchView = menu.findItem(R.id.search_button)?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                myAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun loadJSONFromAsset(): String {
        val json: String
        json = try {
            val inputStream: InputStream = activity!!.assets.open("MoviesList.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(context, FilmActivity::class.java).apply {
            putExtra("Title", movies[position].title)
            putExtra("Year", movies[position].year)
            putExtra("imdbID", movies[position].imdbID)
            putExtra("Rated", movies[position].rated)
            putExtra("Released", movies[position].released)
            putExtra("Runtime", movies[position].runtime)
            putExtra("Genre", movies[position].genre)
            putExtra("Director", movies[position].director)
            putExtra("Writer", movies[position].writer)
            putExtra("Actors", movies[position].actors)
            putExtra("Plot", movies[position].plot)
            putExtra("Language", movies[position].language)
            putExtra("Country", movies[position].country)
            putExtra("Awards", movies[position].awards)
            putExtra("imdbRating", movies[position].imdbRating)
            putExtra("imdbVotes", movies[position].imdbVotes)
            putExtra("Production", movies[position].production)
            putExtra("Type", movies[position].type)
            putExtra("Poster", movies[position].poster)
        }
        startActivity(intent)
    }
}