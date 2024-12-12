package com.example.minijuegos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minijuegos.model.HighScore

class HighScoresAdapter(private var highScoresList: List<HighScore>) :
    RecyclerView.Adapter<HighScoresAdapter.HighScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_high_score, parent, false)
        return HighScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        val highScore = highScoresList[position]
        holder.bind(highScore)
    }

    override fun getItemCount(): Int = highScoresList.size

    // Actualizar la lista de puntajes
    fun updateScores(newScores: List<HighScore>) {
        highScoresList = newScores
        notifyDataSetChanged()
    }

    // ViewHolder para representar cada puntaje
    class HighScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPlayerName: TextView = itemView.findViewById(R.id.tvPlayerName)
        private val tvScore: TextView = itemView.findViewById(R.id.tvScore)

        fun bind(highScore: HighScore) {
            tvPlayerName.text = highScore.playerName
            tvScore.text = highScore.score.toString()
        }
    }
}
