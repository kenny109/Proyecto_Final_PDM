package com.example.minijuegos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Tictactoe : AppCompatActivity() {

    private var turn = "X" // siempre empieza X
    private val board = Array(3) { arrayOfNulls<ImageButton>(3) } // El tablero es una matriz de botones
    private var gameEnded = false // Esto es para saber si ya hay un ganador o no

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tictactoe)

        // Conectamos los botones del XML al código para que el tablero funcione
        board[0][0] = findViewById(R.id.button1)
        board[0][1] = findViewById(R.id.button2)
        board[0][2] = findViewById(R.id.button3)
        board[1][0] = findViewById(R.id.button4)
        board[1][1] = findViewById(R.id.button5)
        board[1][2] = findViewById(R.id.button6)
        board[2][0] = findViewById(R.id.button7)
        board[2][1] = findViewById(R.id.button8)
        board[2][2] = findViewById(R.id.button9)

        // Le ponemos un clic a cada botón del tablero
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j]?.setOnClickListener {
                    onCellClicked(it as ImageButton, i, j)
                }
            }
        }

        // Botón de reiniciar
        val restartButton: Button = findViewById(R.id.restartButton)
        restartButton.setOnClickListener {
            restartGame()
        }

        // Botón para volver al MainActivity
        val volverButton: Button = findViewById(R.id.volverButton)
        volverButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cerramos esta actividad
        }
    }

    private fun onCellClicked(button: ImageButton, row: Int, col: Int) {
        if (gameEnded || button.tag != null) return

        button.tag = turn
        if (turn == "X") {
            button.setImageResource(R.drawable.ic_cross) // Colocamos una X
        } else {
            button.setImageResource(R.drawable.ic_circle) // Colocamos un O
        }

        if (checkWinner()) {
            // Mostramos quién ganó y bloqueamos el tablero
            Toast.makeText(this, "¡${turn} ha ganado!", Toast.LENGTH_SHORT).show()
            gameEnded = true
        } else {
            // Cambiamos el turno
            turn = if (turn == "X") "O" else "X"
        }
    }

    private fun checkWinner(): Boolean {
        // Revisamos todas las filas, columnas y diagonales para ver si alguien gano
        for (i in 0..2) {
            if (board[i][0]?.tag == board[i][1]?.tag && board[i][1]?.tag == board[i][2]?.tag && board[i][0]?.tag != null)
                return true
            if (board[0][i]?.tag == board[1][i]?.tag && board[1][i]?.tag == board[2][i]?.tag && board[0][i]?.tag != null)
                return true
        }
        if (board[0][0]?.tag == board[1][1]?.tag && board[1][1]?.tag == board[2][2]?.tag && board[0][0]?.tag != null)
            return true
        if (board[0][2]?.tag == board[1][1]?.tag && board[1][1]?.tag == board[2][0]?.tag && board[0][2]?.tag != null)
            return true

        return false
    }

    private fun restartGame() {
        // Esto limpia el tablero y resetea todo
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j]?.setImageResource(0) // Quitamos la imagen de la celda
                board[i][j]?.tag = null // Borramos la marca de la celda
            }
        }
        turn = "X" // Siempre empieza X
        gameEnded = false // El juego está listo para empezar de nuevo
    }
}
