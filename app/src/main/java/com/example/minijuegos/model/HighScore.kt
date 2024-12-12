package com.example.minijuegos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "high_score_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val gameName: String,
    val score: Int,
    val playerName: String
)
