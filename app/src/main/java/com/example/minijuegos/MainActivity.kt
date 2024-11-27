package com.example.minijuegos
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardMemoryPairs = findViewById<CardView>(R.id.cardMemoryPairs)
        val cardConnectColors = findViewById<CardView>(R.id.cardConnectColors)
        val cardSoundMemory = findViewById<CardView>(R.id.cardSoundMemory)
        val cardReachStars = findViewById<CardView>(R.id.cardReachStars)

        cardMemoryPairs.setOnClickListener {
            val intent = Intent(this, MemoryPairsActivity::class.java)
            startActivity(intent)
        }

        cardConnectColors.setOnClickListener {
            val intent = Intent(this, ConnectColorsActivity::class.java)
            startActivity(intent)
        }

        cardSoundMemory.setOnClickListener {
            val intent = Intent(this, SoundMemoryActivity::class.java)
            startActivity(intent)
        }

        cardReachStars.setOnClickListener {
            val intent = Intent(this, Tictactoe::class.java)
            startActivity(intent)
        }
    }
}
