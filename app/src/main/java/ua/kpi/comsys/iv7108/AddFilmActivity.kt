package ua.kpi.comsys.iv7108

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AddFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)
    }

    fun addNewFilm(view: View) {
        val title: EditText = findViewById(R.id.editTextTextPersonName)
        val type: EditText = findViewById(R.id.editTextTextPersonName2)
        val year: EditText = findViewById(R.id.editTextNumber)
        val intent = Intent()
        intent.putExtra("title", title.text.toString())
        intent.putExtra("type", type.text.toString())
        intent.putExtra("year", year.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}