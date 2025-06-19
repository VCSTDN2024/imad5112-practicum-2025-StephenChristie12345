package com.example.musicmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val songTitles = Array(4) { "" }
    private val artistNames = Array(4) { "" }
    private val ratings = IntArray(4) { 0 }
    private val comments = Array(4) { "" }
    private var songCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.add_button)
        val viewButton = findViewById<Button>(R.id.view_button)
        val exitButton = findViewById<Button>(R.id.exit_button)

        addButton.setOnClickListener { addSong() }

        viewButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("songs", songTitles)
            intent.putExtra("artists", artistNames)
            intent.putExtra("ratings", ratings)
            intent.putExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener { finish() }
    }

    private fun addSong() {
        if (songCount >= 4) {
            Toast.makeText(this, "Playlist full (max 4 songs)", Toast.LENGTH_SHORT).show()
            return
        }

        val titleInput = findViewById<EditText>(R.id.editTitle).text.toString().trim()
        val artistInput = findViewById<EditText>(R.id.editArtist).text.toString().trim()
        val ratingInput = findViewById<EditText>(R.id.editRating).text.toString().trim()
        val commentInput = findViewById<EditText>(R.id.editComment).text.toString().trim()

        if (titleInput.isEmpty() || artistInput.isEmpty() || ratingInput.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val rating = ratingInput.toIntOrNull()
        if (rating == null || rating !in 1..5) {
            Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
            return
        }

        songTitles[songCount] = titleInput
        artistNames[songCount] = artistInput
        ratings[songCount] = rating
        comments[songCount] = commentInput
        songCount++

        Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()

        // Clear inputs
        findViewById<EditText>(R.id.editTitle).text.clear()
        findViewById<EditText>(R.id.editArtist).text.clear()
        findViewById<EditText>(R.id.editRating).text.clear()
        findViewById<EditText>(R.id.editComment).text.clear()
    }
}

