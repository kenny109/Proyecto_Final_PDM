//Archivo creado por Marycielo Guadalupe Bedoya Pinto
//Ultima modificación:28/11/24
//JUego de memoria de pares, en donde se busca dos imagenes identicas y se acumula puntos
package com.example.minijuegos

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.util.*

class MemoryPairsFragment : Fragment() {
    private lateinit var cartasViews: List<ImageView>
    private lateinit var player1TextView: TextView
    private lateinit var player2TextView: TextView

    private var turno: Int = 1
    private var puntosPlayer1: Int = 0
    private var puntosPlayer2: Int = 0
    private var primeraCarta: Int = 0
    private var segundaCarta: Int = 0
    private var primerClick: Int = 0
    private var segundoClick: Int = 0
    private var numeroCarta: Int = 1
    private var cartas = ArrayList<Int>(listOf(11, 12, 13, 14, 15, 21, 22, 23, 24, 25))

    private var imagen11: Int = 0
    private var imagen12: Int = 0
    private var imagen13: Int = 0
    private var imagen14: Int = 0
    private var imagen15: Int = 0

    private var imagen21: Int = 0
    private var imagen22: Int = 0
    private var imagen23: Int = 0
    private var imagen24: Int = 0
    private var imagen25: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_memorypairs, container, false)

        // Inicializar vistas
        player1TextView = view.findViewById(R.id.mainActivityTvPlayer1)
        player2TextView = view.findViewById(R.id.mainActivityTvPlayer2)
        cartasViews = listOf(
            view.findViewById(R.id.im11),
            view.findViewById(R.id.im12),
            view.findViewById(R.id.im13),
            view.findViewById(R.id.im21),
            view.findViewById(R.id.im22),
            view.findViewById(R.id.im23),
            view.findViewById(R.id.im31),
            view.findViewById(R.id.im32),
            view.findViewById(R.id.im33),
            view.findViewById(R.id.im41)
        )

        // Setup UI
        setUpUi()
        // Cargar cartas
        cargarCartas()
        // Barajar cartas
        Collections.shuffle(cartas)
        // Setup OnClickListener
        setUpOnClickListener()

        return view
    }

    private fun setUpUi() {
        player1TextView.setTextColor(Color.GREEN)
        player1TextView.setTypeface(null, Typeface.BOLD)

        player2TextView.setTextColor(Color.GRAY)
        player2TextView.setTypeface(null, Typeface.NORMAL)
    }

    private fun cargarCartas() {
        imagen11 = R.drawable.ic_bicle
        imagen12 = R.drawable.ic_barco
        imagen13 = R.drawable.ic_coche
        imagen14 = R.drawable.ic_avion
        imagen15 = R.drawable.ic_tren

        imagen21 = R.drawable.ic_bicle
        imagen22 = R.drawable.ic_barco
        imagen23 = R.drawable.ic_coche
        imagen24 = R.drawable.ic_avion
        imagen25 = R.drawable.ic_tren
    }

    private fun setUpOnClickListener() {
        cartasViews.forEachIndexed { index, imageView ->
            imageView.setOnClickListener { asignarImagenALaCarta(imageView, index) }
        }
    }

    private fun asignarImagenALaCarta(image: ImageView, carta: Int) {
        when (cartas[carta]) {
            11 -> image.setImageResource(imagen11)
            12 -> image.setImageResource(imagen12)
            13 -> image.setImageResource(imagen13)
            14 -> image.setImageResource(imagen14)
            15 -> image.setImageResource(imagen15)
            21 -> image.setImageResource(imagen21)
            22 -> image.setImageResource(imagen22)
            23 -> image.setImageResource(imagen23)
            24 -> image.setImageResource(imagen24)
            25 -> image.setImageResource(imagen25)
        }
        if (numeroCarta == 1) {
            primeraCarta = cartas[carta]
            if (primeraCarta > 20) {
                primeraCarta -= 10
            }
            numeroCarta = 2
            primerClick = carta
            image.isEnabled = false
        } else if (numeroCarta == 2) {
            segundaCarta = cartas[carta]
            if (segundaCarta > 20) {
                segundaCarta -= 10
            }
            numeroCarta = 1
            segundoClick = carta
            bloquearCartas()
            val handler = Handler()
            handler.postDelayed({ comprobarCorrecto() }, 1000)
        }
    }

    private fun bloquearCartas() {
        cartasViews.forEach { it.isEnabled = false }
    }

    private fun comprobarCorrecto() {
        if (primeraCarta == segundaCarta) {
            listOf(primerClick, segundoClick).forEach {
                cartasViews[it].visibility = View.INVISIBLE
            }
            actualizarPuntajes()
        } else {
            reiniciarCartas()
        }
        desbloquearCartas()
        finPartida()
    }

    private fun actualizarPuntajes() {
        if (turno == 1) {
            puntosPlayer1++
            player1TextView.text = "Player 1: $puntosPlayer1"
        } else {
            puntosPlayer2++
            player2TextView.text = "Player 2: $puntosPlayer2"
        }
    }

    private fun reiniciarCartas() {
        cartasViews.forEach { it.setImageResource(R.drawable.ic_caja_negra) }
        cambiarTurno()
    }

    private fun cambiarTurno() {
        if (turno == 1) {
            turno = 2
            player1TextView.setTextColor(Color.GRAY)
            player1TextView.setTypeface(null, Typeface.NORMAL)
            player2TextView.setTextColor(Color.RED)
            player2TextView.setTypeface(null, Typeface.BOLD)
        } else {
            turno = 1
            player1TextView.setTextColor(Color.GREEN)
            player1TextView.setTypeface(null, Typeface.BOLD)
            player2TextView.setTextColor(Color.GRAY)
            player2TextView.setTypeface(null, Typeface.NORMAL)
        }
    }


    private fun desbloquearCartas() {
        cartasViews.forEach { it.isEnabled = true }
    }

    private fun finPartida() {
        if (cartasViews.all { it.visibility == View.INVISIBLE }) {
            val alertDialog = AlertDialog.Builder(requireContext()).create()
            alertDialog.setTitle("Fin de partida")
            alertDialog.setMessage("Player 1: $puntosPlayer1\nPlayer 2: $puntosPlayer2")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Jugar de nuevo") { _, _ ->
                requireActivity().recreate()
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Salir") { _, _ ->
                requireActivity().finish()
            }
            alertDialog.show()
        }
    }
}
