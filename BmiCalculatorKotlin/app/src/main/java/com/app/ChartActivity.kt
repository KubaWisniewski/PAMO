package com.app

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import java.util.ArrayList

open class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        val backButton = findViewById<Button>(R.id.backButton)
        val chartWebView = findViewById<WebView>(R.id.chartWebView)
        val intent = intent
        val statistics = intent.getStringArrayListExtra("statistics")
        val value = getValuesString(statistics!!)
        val webSettings = chartWebView.settings
        webSettings.javaScriptEnabled = true
        val htmlData = ("<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load('current', {'packages':['corechart']});"
                + "      google.charts.setOnLoadCallback(drawChart);"
                + "      function drawChart() {"
                + "        var data = google.visualization.arrayToDataTable(["
                + "          ['Kg', 'Waga']," + value + "]);"
                + "        var options = {\n"
                + "          title: 'Zmiana wagi w czasie',\n"
                + "          curveType: 'function',\n"
                + "          legend: { position: 'bottom' }\n"
                + "        };\n"
                + "        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));"
                + "        chart.draw(data, options);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"curve_chart\" style=\"width: 375px; height: 480px;\"></div>"
                + "  </body>"
                + "</html>")
        chartWebView.loadData(htmlData, "text/html", "UTF-8")
        backButton.setOnClickListener { finish() }
    }

    private fun getValuesString(statistics: ArrayList<String>): String {
        val result = StringBuilder()
        for (i in statistics.indices) {
            result.append("['Pomiar ").append(i).append("',")
                .append(java.lang.Float.valueOf(statistics[i])).append("],")
        }
        return result.toString()
    }
}
