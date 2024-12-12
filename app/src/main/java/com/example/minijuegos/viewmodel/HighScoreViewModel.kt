package com.example.minijuegos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.minijuegos.data.AppDatabase
import com.example.minijuegos.data.HighScoreDao
import com.example.minijuegos.model.HighScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HighScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val highScoreDao: HighScoreDao = AppDatabase.getDatabase(application).highScoreDao()

    // LiveData para obtener los mejores puntajes
    fun getTopScores(gameName: String): LiveData<List<HighScore>> {
        val scores = MutableLiveData<List<HighScore>>()
        viewModelScope.launch(Dispatchers.IO) {
            scores.postValue(highScoreDao.getTopScoresForGame(gameName))
        }
        return scores
    }

    // Insertar un puntaje
    fun insertScore(highScore: HighScore) {
        viewModelScope.launch(Dispatchers.IO) {
            highScoreDao.insert(highScore)
        }
    }
}
