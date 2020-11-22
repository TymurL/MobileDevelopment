package ua.kpi.comsys.iv7108

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)
    }

    fun addNewFilm(view: View) {
        val title: String = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        val type: String = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
        val year: String = findViewById<EditText>(R.id.editTextNumber).text.toString()
        if (title.isNotEmpty() && type.isNotEmpty() && year.isNotEmpty()) {
            val intent = Intent()
            intent.putExtra("title", title)
            intent.putExtra("type", type)
            intent.putExtra("year", year)
            setResult(RESULT_OK, intent)
            finish()
        } else {
            Toast.makeText(this, "Please fill parameters", Toast.LENGTH_SHORT).show()
        }
    }
}