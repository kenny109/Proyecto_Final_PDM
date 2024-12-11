package com.example.minijuegos

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Button
import androidx.fragment.app.Fragment

class SoundMemoryFragment : Fragment() {

    private val soundSequence = mutableListOf<Int>()
    private val currentPlayerSequence = mutableListOf<Int>()
    private var currentRound = 1
    private var score = 0
    private var isGameOver = false

    // Botones del juego
    private lateinit var soundButtons: List<ImageView>  // Usando ImageView en lugar de ImageButton
    private lateinit var btnDrums: ImageView
    private lateinit var tvRoundInfo: TextView

    // Botones adicionales
    private lateinit var btnBack: Button  // Usando Button para "Volver"
    private lateinit var btnRestart: Button  // Usando Button para "Reiniciar"

    private val soundFiles = listOf(
        R.raw.guitar,
        R.raw.piano,
        R.raw.trumpet,
        R.raw.drums
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar la vista del fragmento
        val rootView = inflater.inflate(R.layout.fragment_soundmemory, container, false)

        // Inicialización de los botones y TextView
        soundButtons = listOf(
            rootView.findViewById(R.id.btnGuitar),
            rootView.findViewById(R.id.btnPiano),
            rootView.findViewById(R.id.btnTrumpet)
        )

        btnDrums = rootView.findViewById(R.id.btnDrums)
        tvRoundInfo = rootView.findViewById(R.id.tvRoundInfo)

        // Botones adicionales
        btnBack = rootView.findViewById(R.id.btnBack)
        btnRestart = rootView.findViewById(R.id.btnRestart)

        // Configurar listeners para los botones de sonidos
        soundButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!isGameOver) {
                    checkPlayerInput(index)
                }
            }
        }

        btnDrums.setOnClickListener {
            if (!isGameOver) {
                checkPlayerInput(3)
            }
        }

        // Configurar listeners para los botones adicionales
        btnBack.setOnClickListener {
            activity?.onBackPressed()  // Regresar a la actividad anterior
        }

        btnRestart.setOnClickListener {
            restartGame()  // Reiniciar el juego
        }

        // Iniciar el juego
        startRound()

        return rootView
    }

    private fun startRound() {
        val randomSound = (0 until soundFiles.size).random()
        soundSequence.add(randomSound)

        tvRoundInfo.text = "Ronda: $currentRound"
        playSoundSequence()
    }

    private fun playSoundSequence() {
        currentPlayerSequence.clear()

        val handler = Handler()
        var delay = 0L

        enableButtons(false)

        for (soundIndex in soundSequence) {
            handler.postDelayed({
                val mediaPlayer = MediaPlayer.create(context, soundFiles[soundIndex])
                mediaPlayer.start()

                mediaPlayer.setOnCompletionListener {
                    mediaPlayer.release()
                }
            }, delay)

            delay += 2500
        }

        handler.postDelayed({
            enableButtons(true)
        }, delay)
    }

    private fun enableButtons(enable: Boolean) {
        soundButtons.forEach { button ->
            button.isEnabled = enable
        }
        btnDrums.isEnabled = enable
    }

    private fun checkPlayerInput(clickedIndex: Int) {
        if (currentPlayerSequence.size < soundSequence.size) {
            if (clickedIndex == soundSequence[currentPlayerSequence.size]) {
                currentPlayerSequence.add(clickedIndex)
                if (currentPlayerSequence.size == soundSequence.size) {
                    score++
                    currentRound++
                    isGameOver = false
                    startRound()
                }
            } else {
                isGameOver = true
                showGameOver()
            }
        }
    }

    private fun showGameOver() {
        Toast.makeText(context, "Game Over! Final Score: $score", Toast.LENGTH_LONG).show()
    }

    // Método para reiniciar el juego
    private fun restartGame() {
        soundSequence.clear()
        currentPlayerSequence.clear()
        currentRound = 1
        score = 0
        isGameOver = false
        startRound()  // Comienza de nuevo
    }
}
