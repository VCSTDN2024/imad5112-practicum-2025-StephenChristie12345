package com.example.musicplaylist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity2: AppCompatActivity() {

    private lateinit var songTitles: Array<String>
    private lateinit var artistNames: Array<String>
    private lateinit var ratings: IntArray
    private lateinit var comments: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val txtDisplay = findViewById<TextView>(R.id.txtDisplay)
        val btnList = findViewById<Button>(R.id.btnList)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Retrieve data from Intent
        songTitles = intent.getStringArrayExtra("songs")!!
        artistNames = intent.getStringArrayExtra("artists")!!
        ratings = intent.getIntArrayExtra("ratings")!!
        comments = intent.getStringArrayExtra("comments")!!

        // Show the playlist
        btnList.setOnClickListener {
            val builder = StringBuilder()
            for (i in songTitles.indices) {
                if (songTitles[i].isNotEmpty()) {
                    builder.append("${songTitles[i]} by ${artistNames[i]}\n")
                    builder.append("Rating: ${ratings[i]} | ${comments[i]}\n\n")
                }
            }
            txtDisplay.text = builder.toString()
        }

        // Show average rating
        btnAverage.setOnClickListener {
            var total = 0
            var count = 0
            for (rate in ratings) {
                if (rate > 0) {
                    total += rate
                    count++
                }
            }
            val avg = if (count > 0) total.toDouble() / count else 0.0
            txtDisplay.text = "Average Rating: %.2f".format(avg)
        }

        // Return to main screen
        btnBack.setOnClickListener {
            finish()
        }
    }
}
