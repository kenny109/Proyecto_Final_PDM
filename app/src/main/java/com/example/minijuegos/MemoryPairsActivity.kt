package com.example.minijuegos
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MemoryPairsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorypairs)

        val gameTitle = findViewById<TextView>(R.id.gameTitle)
        val gameStatus = findViewById<TextView>(R.id.gameStatus)

        gameTitle.text = "Memoria de Pares"
        gameStatus.text = "Coming soon (aun en progreso)"
    }
}