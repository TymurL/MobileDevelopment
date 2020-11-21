package ua.kpi.comsys.iv7108

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 */
class MyItemRecyclerViewAdapter(
    private val valuesFull: List<Movie>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>(), Filterable {

    private val values: MutableList<Movie> = valuesFull.toMutableList()

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
        holder.poster.setImageResource(
            holder.poster.context.resources.getIdentifier(
                if (item.poster.isNotEmpty()) item.poster.substring(0, item.poster.lastIndexOf('.'))
                    .toLowerCase() else "",
                "drawable",
                holder.poster.context.packageName
            )
        )
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val title: TextView = view.findViewById(R.id.titleView)
        val year: TextView = view.findViewById(R.id.yearView)
        val imdbID: TextView = view.findViewById(R.id.imdbIDView)
        val type: TextView = view.findViewById(R.id.typeView)
        val poster: ImageView = view.findViewById(R.id.posterView)

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getFilter(): Filter {
        return myFilter
    }

    private val myFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<Movie> = ArrayList();

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(valuesFull)
            } else {
                for (movie in valuesFull) {
                    if (movie.title.contains(constraint, true)) {
                        filteredList.add(movie)
                    }
                }
            }

            val filterResult = FilterResults()
            filterResult.values = filteredList
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            values.clear()
            if (results != null) {
                values.addAll(results.values as Collection<Movie>)
            }
            notifyDataSetChanged()
        }
    }
}