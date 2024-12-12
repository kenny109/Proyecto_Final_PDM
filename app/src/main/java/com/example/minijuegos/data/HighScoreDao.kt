package com.example.minijuegos.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.minijuegos.model.HighScore

@Dao
interface HighScoreDao {

    @Insert
    suspend fun insert(highScore: HighScore)

    @Query("SELECT * FROM high_score_table WHERE gameName = :gameName ORDER BY score DESC LIMIT 3")
    suspend fun getTopScoresForGame(gameName: String): List<HighScore>
}
