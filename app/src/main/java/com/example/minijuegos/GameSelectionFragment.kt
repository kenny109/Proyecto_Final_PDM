package com.example.minijuegos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

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
        val cardViewHighScores = view.findViewById<CardView>(R.id.cardViewHighScores)

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

        // Configuración para el botón de puntajes
        cardViewHighScores.setOnClickListener {
            navigateToFragment(HighScoresFragment()) // Aquí se navega al fragmento de puntajes
        }
    }

    // Método de navegación (puedes adaptarlo según tu implementación)
    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Asegúrate que fragmentContainer exista en el layout principal
            .addToBackStack(null)
            .commit()
    }
}
