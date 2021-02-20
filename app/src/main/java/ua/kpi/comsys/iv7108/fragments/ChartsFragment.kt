package ua.kpi.comsys.iv7108.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import ua.kpi.comsys.iv7108.R
import kotlin.math.pow


class ChartsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_charts, container, false)

        val lineChart = view.findViewById<LineChart>(R.id.line_chart)
        val lineDataSet = LineDataSet(getLineDataSet(), "y = e ^ x")
        lineDataSet.setDrawValues(false)
        lineChart.data = LineData(lineDataSet)
        lineChart.description.isEnabled = false
        lineChart.invalidate()

        val pieChart = view.findViewById<PieChart>(R.id.pie_chart)
        val pieDataSet = PieDataSet(getPieDataSet(), "Pie Chart")
        pieDataSet.setColors(Color.GREEN, Color.YELLOW, Color.RED)
        pieChart.data = PieData(pieDataSet)
        pieChart.description.isEnabled = false
        pieChart.invalidate()

        return view
    }

    private fun getLineDataSet(): List<Entry> {
        val entries = ArrayList<Entry>()
        var x = -6.0
        while (x < 6.0) {
            val y = Math.E.pow(x)
            x += 0.5
            entries.add(Entry(x.toFloat(), y.toFloat()))
        }
        return entries
    }

    private fun getPieDataSet(): List<PieEntry> {
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(35f, "a"))
        entries.add(PieEntry(40f, "b"))
        entries.add(PieEntry(25f, "c"))
        return entries
    }
}