package ua.kpi.comsys.iv7108

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class HomeFragment : Fragment() {

    private lateinit var photoAdapter: PhotoAdapter
    private val photoList = mutableListOf<PhotoGrid>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        if (view is RecyclerView) {
            photoAdapter = PhotoAdapter(photoList as List<PhotoGrid>)
            loadPhotos()
            view.adapter = photoAdapter
        }
        return view
    }

    private fun loadPhotos() {
        val queue = Volley.newRequestQueue(context)
        val url =
            "https://pixabay.com/api/?key=19193969-87191e5db266905fe8936d565&q=fun+party&image_type=photo&per_page=30"
        val request = JsonObjectRequest(
            url,
            null,
            { response ->
                val images = response.getJSONArray("hits")
                for (i in 0 until images.length() / 10) {
                    photoList.add(
                        PhotoGrid(
                            images.getJSONObject(0 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(1 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(2 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(3 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(4 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(5 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(6 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(7 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(8 + 10 * i).getString("webformatURL"),
                            images.getJSONObject(9 + 10 * i).getString("webformatURL")
                        )
                    )
                }
                photoAdapter.notifyDataSetChanged()
            },
            { error -> error.printStackTrace() })
        queue.add(request)
    }
}