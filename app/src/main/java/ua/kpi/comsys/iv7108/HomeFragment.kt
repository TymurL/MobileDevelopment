package ua.kpi.comsys.iv7108

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private val photoAdapter = PhotoAdapter()
    private val photoList = mutableListOf<PhotoGrid>()
    private var numberOfPhotoInGrid = 0

    init {
        photoAdapter.submitList(photoList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.add_photo_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        if (view is RecyclerView) {
            view.adapter = photoAdapter
        }
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_button -> {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0)
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
                addPhoto(data.data)
            }
        }
    }

    private fun addPhoto(uri: Uri?) {
        if (photoList.isEmpty() || numberOfPhotoInGrid == 0) {
            val index = photoList.size
            photoList.add(
                index,
                PhotoGrid(
                    photo1 = uri,
                    photo2 = null,
                    photo3 = null,
                    photo4 = null,
                    photo5 = null,
                    photo6 = null,
                    photo7 = null,
                    photo8 = null,
                    photo9 = null,
                    photo10 = null
                )
            )
            numberOfPhotoInGrid++
            photoAdapter.notifyItemInserted(index)
        } else {
            val index = photoList.lastIndex
            when (numberOfPhotoInGrid) {
                1 -> photoList[index] = photoList[index].copy(photo2 = uri)
                2 -> photoList[index] = photoList[index].copy(photo3 = uri)
                3 -> photoList[index] = photoList[index].copy(photo4 = uri)
                4 -> photoList[index] = photoList[index].copy(photo5 = uri)
                5 -> photoList[index] = photoList[index].copy(photo6 = uri)
                6 -> photoList[index] = photoList[index].copy(photo7 = uri)
                7 -> photoList[index] = photoList[index].copy(photo8 = uri)
                8 -> photoList[index] = photoList[index].copy(photo9 = uri)
                9 -> {
                    photoList[index] = photoList[index].copy(photo10 = uri)
                    numberOfPhotoInGrid = -1
                }
            }
            numberOfPhotoInGrid++
            photoAdapter.notifyItemChanged(photoList.lastIndex)
        }
    }
}