package ua.kpi.comsys.iv7108

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter : ListAdapter<PhotoGrid, PhotoAdapter.PhotoGridViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PhotoGrid>() {
            override fun areItemsTheSame(
                oldItem: PhotoGrid,
                newItem: PhotoGrid
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PhotoGrid,
                newItem: PhotoGrid
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.PhotoGridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return PhotoGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoGridViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotoGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(grid: PhotoGrid) {
            itemView.findViewById<ImageView>(R.id.img1).setImageURI(grid.photo1)
            itemView.findViewById<ImageView>(R.id.img2).setImageURI(grid.photo2)
            itemView.findViewById<ImageView>(R.id.img3).setImageURI(grid.photo3)
            itemView.findViewById<ImageView>(R.id.img4).setImageURI(grid.photo4)
            itemView.findViewById<ImageView>(R.id.img5).setImageURI(grid.photo5)
            itemView.findViewById<ImageView>(R.id.img6).setImageURI(grid.photo6)
            itemView.findViewById<ImageView>(R.id.img7).setImageURI(grid.photo7)
            itemView.findViewById<ImageView>(R.id.img8).setImageURI(grid.photo8)
            itemView.findViewById<ImageView>(R.id.img9).setImageURI(grid.photo9)
            itemView.findViewById<ImageView>(R.id.img10).setImageURI(grid.photo10)
        }
    }
}