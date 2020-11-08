package ua.kpi.comsys.iv7108

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

//        findViewById<TextView>(R.id.textView).apply {
//            text = """
//                   Current time ${TimeTL(Date())}
//                   Empty constructor ${TimeTL()}
//                   Constructor with given values ${TimeTL(12, 0, 0)}
//                   Add 13:30:45 and 2:30:15 ${TimeTL(13, 30, 45).add(TimeTL(2, 30, 15))}
//                   Diff 10:00:00 and 5:00:01 ${TimeTL(10, 0, 0).diff(TimeTL(5, 0, 1))}
//                   """.trimIndent()
//        }
    }
}