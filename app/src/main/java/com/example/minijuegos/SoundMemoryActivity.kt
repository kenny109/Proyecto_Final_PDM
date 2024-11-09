package com.example.minijuegos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class SoundMemoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soundmemory)

        val gameTitle = findViewById<TextView>(R.id.gameTitle)
        val gameStatus = findViewById<TextView>(R.id.gameStatus)

        gameTitle.text = "Memoria del Sonido"
        gameStatus.text = "Coming soon (aun en progreso)"
    }
}