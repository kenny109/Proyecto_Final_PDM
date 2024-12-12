package com.example.minijuegos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minijuegos.viewmodel.HighScoreViewModel

class HighScoresFragment : Fragment() {

    private lateinit var highScoreViewModel: HighScoreViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HighScoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragment_high_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewHighScores)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar el adaptador vacío (se llenará más adelante)
        adapter = HighScoresAdapter(emptyList())
        recyclerView.adapter = adapter

        // Obtener el ViewModel
        highScoreViewModel = ViewModelProvider(this)[HighScoreViewModel::class.java]

        // Observar los puntajes altos desde el ViewModel
        highScoreViewModel.getTopScores("Memory Pairs").observe(viewLifecycleOwner) { highScores ->
            // Actualizar el adaptador con los nuevos datos
            adapter.updateScores(highScores)
        }
    }
}
