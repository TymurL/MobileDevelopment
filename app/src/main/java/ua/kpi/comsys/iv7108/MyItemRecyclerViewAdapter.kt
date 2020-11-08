package ua.kpi.comsys.iv7108

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 */
class MyItemRecyclerViewAdapter(
    private val values: List<Movie>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleView)
        val year: TextView = view.findViewById(R.id.yearView)
        val imdbID: TextView = view.findViewById(R.id.imdbIDView)
        val type: TextView = view.findViewById(R.id.typeView)
        val poster: ImageView = view.findViewById(R.id.posterView)

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }
}