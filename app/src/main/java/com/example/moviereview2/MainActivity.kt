package com.example.moviereview2

import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView
    private var selectedMovieTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        movieRecyclerView = findViewById(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(this)

        val movies = listOf(
            "Inception", "The Matrix", "Interstellar", "Rocky Aur Raani Ki Prem Kahaani",
            "Salaar", "Kalki", "Conjuring", "EvilDead", "The Nun", "Shershah",
            "Ek Villain", "What Lies Below", "Kabhi Khushi Kabhi Gham", "Kuck Kuch Hota Hai",
            "Maine Pyaar Kiya", "Koi Mil Gaya", "RaOne", "Pink", "Aranmanai 4"
        )

        val adapter = MovieAdapter(movies) { movieTitle ->
            selectedMovieTitle = movieTitle // Store the selected movie title
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("movieTitle", movieTitle)
            startActivity(intent)
        }
        movieRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                selectedMovieTitle?.let { movieTitle ->
                    val intent = Intent(this, ReviewActivity::class.java)
                    intent.putExtra("movieTitle", movieTitle)
                    startActivity(intent)
                }
                true
            }

            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item) // This is the else branch
        }
    }
}
