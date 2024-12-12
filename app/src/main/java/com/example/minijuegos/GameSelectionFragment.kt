package com.example.minijuegos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import android.widget.Button

class GameSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño para este fragmento
        return inflater.inflate(R.layout.fragment_game_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Encuentra las tarjetas y configura los clics
        val cardMemoryPairs = view.findViewById<CardView>(R.id.cardMemoryPairs)
        val cardConnectColors = view.findViewById<CardView>(R.id.cardTicTacToe)
        val cardSoundMemory = view.findViewById<CardView>(R.id.cardSoundMemory)
        val cardReachStars = view.findViewById<CardView>(R.id.cardReachStars)

        // Configuración de botones de juegos
        cardMemoryPairs.setOnClickListener {
            navigateToFragment(MemoryPairsFragment())
        }

        cardConnectColors.setOnClickListener {
            navigateToFragment(TictactoeFragment())
        }

        cardSoundMemory.setOnClickListener {
            navigateToFragment(SoundMemoryFragment())
        }

        cardReachStars.setOnClickListener {
            navigateToFragment(ReachStarsFragment())
        }

        // Configuración para el botón de puntajes
        val btnViewHighScores = view.findViewById<Button>(R.id.btnViewHighScores)
        btnViewHighScores.setOnClickListener {
            navigateToFragment(HighScoresFragment()) // Aquí se navega al fragmento de puntajes
        }
    }

    // Método de navegación (puedes adaptarlo según tu implementación)
    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer , fragment) // Asumiendo que fragment_container es tu contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}

