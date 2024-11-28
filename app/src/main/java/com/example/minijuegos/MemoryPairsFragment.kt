package com.example.minijuegos

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_memory_y_pares.view.*
import java.util.*

class MemoryYParesFragment : Fragment() {

    var turno: Int = 1
    var puntosPlayer1: Int = 0
    var puntosPlayer2: Int = 0
    var primeraCarta: Int = 0
    var segundaCarta: Int = 0
    var primerClick: Int = 0
    var segundoClick: Int = 0
    var numeroCarta: Int = 1
    var cartas = ArrayList<Int>(listOf(11, 12, 13, 14, 15, 21, 22, 23, 24, 25))

    var imagen11: Int = 0
    var imagen12: Int = 0
    var imagen13: Int = 0
    var imagen14: Int = 0
    var imagen15: Int = 0

    var imagen21: Int = 0
    var imagen22: Int = 0
    var imagen23: Int = 0
    var imagen24: Int = 0
    var imagen25: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_memorypairs, container, false)

        // 1.- Setup UI
        setUpUi(view)

        // 2.- Cargar Cartas
        cargarCartas()

        // 3.- Barajar las cartas
        Collections.shuffle(cartas)

        // 4.- Setup OnClickListener
        setUpOnClickListener(view)

        return view
    }

    private fun setUpUi(view: View) {
        view.mainActivityTvPlayer1.setTextColor(Color.GREEN)
        view.mainActivityTvPlayer1.setTypeface(null, Typeface.BOLD)

        view.mainActivityTvPlayer2.setTextColor(Color.GRAY)
        view.mainActivityTvPlayer2.setTypeface(null, Typeface.NORMAL)
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

    private fun setUpOnClickListener(view: View) {
        view.im11.setOnClickListener { asignarImagenALaCarta(view.im11, 0, view) }
        view.im12.setOnClickListener { asignarImagenALaCarta(view.im12, 1, view) }
        view.im13.setOnClickListener { asignarImagenALaCarta(view.im13, 2, view) }
        view.im21.setOnClickListener { asignarImagenALaCarta(view.im21, 3, view) }
        view.im22.setOnClickListener { asignarImagenALaCarta(view.im22, 4, view) }
        view.im23.setOnClickListener { asignarImagenALaCarta(view.im23, 5, view) }
        view.im31.setOnClickListener { asignarImagenALaCarta(view.im31, 6, view) }
        view.im32.setOnClickListener { asignarImagenALaCarta(view.im32, 7, view) }
        view.im33.setOnClickListener { asignarImagenALaCarta(view.im33, 8, view) }
        view.im41.setOnClickListener { asignarImagenALaCarta(view.im41, 9, view) }
    }

    private fun asignarImagenALaCarta(image: ImageView, carta: Int, view: View) {
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
            bloquearCartas(view)
            val handler = Handler()
            handler.postDelayed({ comprobarCorrecto(view) }, 1000)
        }
    }

    private fun bloquearCartas(view: View) {
        listOf(view.im11, view.im12, view.im13, view.im21, view.im22, view.im23, view.im31, view.im32, view.im33, view.im41)
            .forEach { it.isEnabled = false }
    }

    private fun comprobarCorrecto(view: View) {
        if (primeraCarta == segundaCarta) {
            listOf(primerClick, segundoClick).forEach {
                when (it) {
                    0 -> view.im11.visibility = View.INVISIBLE
                    1 -> view.im12.visibility = View.INVISIBLE
                    2 -> view.im13.visibility = View.INVISIBLE
                    3 -> view.im21.visibility = View.INVISIBLE
                    4 -> view.im22.visibility = View.INVISIBLE
                    5 -> view.im23.visibility = View.INVISIBLE
                    6 -> view.im31.visibility = View.INVISIBLE
                    7 -> view.im32.visibility = View.INVISIBLE
                    8 -> view.im33.visibility = View.INVISIBLE
                    9 -> view.im41.visibility = View.INVISIBLE
                }
            }
            actualizarPuntajes(view)
        } else {
            reiniciarCartas(view)
        }
        desbloquearCartas(view)
        finPartida(view)
    }

    private fun actualizarPuntajes(view: View) {
        if (turno == 1) {
            puntosPlayer1++
            view.mainActivityTvPlayer1.text = "Player 1: $puntosPlayer1"
        } else {
            puntosPlayer2++
            view.mainActivityTvPlayer2.text = "Player 2: $puntosPlayer2"
        }
    }

    private fun reiniciarCartas(view: View) {
        listOf(view.im11, view.im12, view.im13, view.im21, view.im22, view.im23, view.im31, view.im32, view.im33, view.im41)
            .forEach { it.setImageResource(R.drawable.ic_caja_negra) }
        cambiarTurno(view)
    }

    private fun cambiarTurno(view: View) {
        if (turno == 1) {
            turno = 2
            view.mainActivityTvPlayer1.setTextColor(Color.GRAY)
            view.mainActivityTvPlayer1.setTypeface(null, Typeface.NORMAL)
            view.mainActivityTvPlayer2.setTextColor(Color.RED)
            view.mainActivityTvPlayer2.setTypeface(null, Typeface.BOLD)
        } else {
            turno = 1
            view.mainActivityTvPlayer1.setTextColor(Color.GREEN)
            view.mainActivityTvPlayer1.setTypeface(null, Typeface.BOLD)
            view.mainActivityTvPlayer2.setTextColor(Color.GRAY)
            view.mainActivityTvPlayer2.setTypeface(null, Typeface.NORMAL)
        }
    }

    private fun desbloquearCartas(view: View) {
        listOf(view.im11, view.im12, view.im13, view.im21, view.im22, view.im23, view.im31, view.im32, view.im33, view.im41)
            .forEach { it.isEnabled = true }
    }

    private fun finPartida(view: View) {
        if (listOf(view.im11, view.im12, view.im13, view.im21, view.im22, view.im23, view.im31, view.im32, view.im33, view.im41)
                .all { it.visibility == View.INVISIBLE }) {
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
