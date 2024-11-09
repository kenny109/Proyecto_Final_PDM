package com.example.minijuegos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ReachStarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reachstars)

        val gameTitle = findViewById<TextView>(R.id.gameTitle)
        val gameStatus = findViewById<TextView>(R.id.gameStatus)

        gameTitle.text = "Llegando a las estrellas"
        gameStatus.text = "Coming soon (aun en progreso)"
    }
}