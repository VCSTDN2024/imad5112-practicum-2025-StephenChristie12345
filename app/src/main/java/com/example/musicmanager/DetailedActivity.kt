package com.example.musicmanager

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailedActivity : AppCompatActivity() {

    private lateinit var songTitles: Array<String>
    private lateinit var artistNames: Array<String>
    private lateinit var ratings: IntArray
    private lateinit var comments: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val txtDisplay = findViewById<TextView>(R.id.txtdisplay)
        val btnList = findViewById<Button>(R.id.btnlist)
        val btnAverage = findViewById<Button>(R.id.btnaverage)
        val btnBack = findViewById<Button>(R.id.btnback)

        // Retrieve data from Intent
        songTitles = intent.getStringArrayExtra("songs") ?: arrayOf()
        artistNames = intent.getStringArrayExtra("artists") ?: arrayOf()
        ratings = intent.getIntArrayExtra("ratings") ?: intArrayOf()
        comments = intent.getStringArrayExtra("comments") ?: arrayOf()

        // Show the playlist
        btnList.setOnClickListener {
            val builder = StringBuilder()
            for (i in songTitles.indices) {
                builder.append("🎵 ${songTitles[i]} by ${artistNames[i]}\n")
                builder.append("Rating: ${ratings[i]}/5\n")
                builder.append("Comment: ${comments[i]}\n\n")
            }
            txtDisplay.text = builder.toString()
        }

        // Calculate average rating
        btnAverage.setOnClickListener {
            if (ratings.isNotEmpty()) {
                val avg = ratings.average()
                txtDisplay.text = " Average Rating: %.2f".format(avg)
            } else {
                txtDisplay.text = "No ratings to average."
            }
        }

        // Go back to previous screen
        btnBack.setOnClickListener {
            finish()
        }
    }
}
