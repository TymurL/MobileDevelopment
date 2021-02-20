package ua.kpi.comsys.iv7108.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kpi.comsys.iv7108.R
import ua.kpi.comsys.iv7108.models.PhotoGrid

class PhotoAdapter(private val list: List<PhotoGrid>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoGridViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return PhotoGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PhotoGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(grid: PhotoGrid) {
            Picasso.get().load(grid.photo1).into(itemView.findViewById<ImageView>(R.id.img1))
            Picasso.get().load(grid.photo2).into(itemView.findViewById<ImageView>(R.id.img2))
            Picasso.get().load(grid.photo3).into(itemView.findViewById<ImageView>(R.id.img3))
            Picasso.get().load(grid.photo4).into(itemView.findViewById<ImageView>(R.id.img4))
            Picasso.get().load(grid.photo5).into(itemView.findViewById<ImageView>(R.id.img5))
            Picasso.get().load(grid.photo6).into(itemView.findViewById<ImageView>(R.id.img6))
            Picasso.get().load(grid.photo7).into(itemView.findViewById<ImageView>(R.id.img7))
            Picasso.get().load(grid.photo8).into(itemView.findViewById<ImageView>(R.id.img8))
            Picasso.get().load(grid.photo9).into(itemView.findViewById<ImageView>(R.id.img9))
            Picasso.get().load(grid.photo10).into(itemView.findViewById<ImageView>(R.id.img10))
        }
    }

    override fun getItemCount(): Int = list.size
}