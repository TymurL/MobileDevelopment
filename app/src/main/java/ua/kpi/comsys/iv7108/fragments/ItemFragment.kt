package ua.kpi.comsys.iv7108.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import ua.kpi.comsys.iv7108.adapters.MyItemRecyclerViewAdapter
import ua.kpi.comsys.iv7108.R
import ua.kpi.comsys.iv7108.SwipeToDeleteCallback
import ua.kpi.comsys.iv7108.activities.AddFilmActivity
import ua.kpi.comsys.iv7108.activities.FilmActivity
import ua.kpi.comsys.iv7108.models.DetailedMovie
import ua.kpi.comsys.iv7108.models.Movie


class ItemFragment : Fragment(), MyItemRecyclerViewAdapter.OnItemClickListener {

    private lateinit var myAdapter: MyItemRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val recyclerView = view.findViewById(R.id.movies) as RecyclerView
        val emptyText = view.findViewById(R.id.empty) as TextView
        recyclerView.visibility = View.GONE
        emptyText.visibility = View.VISIBLE

        myAdapter = MyItemRecyclerViewAdapter(recyclerView.context, this)
        myAdapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if (myAdapter.itemCount == 0) {
                    recyclerView.visibility = View.GONE
                    emptyText.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    emptyText.visibility = View.GONE
                }
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                if (myAdapter.itemCount == 0) {
                    recyclerView.visibility = View.GONE
                    emptyText.visibility = View.VISIBLE
                }
            }
        })

        val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(myAdapter, recyclerView.context))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.adapter = myAdapter

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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_button -> {
            val intent = Intent(context, AddFilmActivity::class.java)
            startActivityForResult(intent, 0)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK && data != null) {
//                val newMovie = Movie(
//                    data.getStringExtra("title")!!,
//                    data.getStringExtra("type")!!,
//                    data.getStringExtra("year")!!
//                )
//                myAdapter.addNewMovie(newMovie)
            }
        }
    }

    override fun onItemClick(movie: Movie) {
        val queue = Volley.newRequestQueue(context)
        val url = "http://www.omdbapi.com/?apikey=7e9fe69e&i=${movie.imdbID}"
        val request = JsonObjectRequest(
            url,
            null,
            { response ->
                val detailedMovie = DetailedMovie(
                    movie,
                    response.getString("Rated"),
                    response.getString("Released"),
                    response.getString("Runtime"),
                    response.getString("Genre"),
                    response.getString("Director"),
                    response.getString("Writer"),
                    response.getString("Actors"),
                    response.getString("Plot"),
                    response.getString("Language"),
                    response.getString("Country"),
                    response.getString("Awards"),
                    response.getString("imdbRating"),
                    response.getString("imdbVotes"),
                    response.getString("Production")
                )
                val intent = Intent(context, FilmActivity::class.java)
                intent.putExtra("movie", detailedMovie)
                startActivity(intent)
            },
            { error -> error.printStackTrace() })
        queue.add(request)
    }
}