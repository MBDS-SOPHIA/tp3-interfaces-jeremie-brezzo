package com.example.diceroller

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Trouver l'EditText dans la disposition
        val numberInput: EditText = findViewById(R.id.editTextNumber)

        // Ajouter un listener pour détecter les changements
        numberInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                rollDice() // Lancer les dés après un changement
            }
        })
    }

    private fun rollDice() {
        val dice1 = Dice(6)
        val dice2 = Dice(6)
        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()

        val resultTextView1: TextView = findViewById(R.id.textView1)
        val resultTextView2: TextView = findViewById(R.id.textView2)
        resultTextView1.text = diceRoll1.toString()
        resultTextView2.text = diceRoll2.toString()

        if (diceRoll1 == diceRoll2) {
            Toast.makeText(this, "Félicitations ! Vous avez gagné !", Toast.LENGTH_SHORT).show()
            animateDice() // Appeler l'animation en cas de victoire
        }
    }

    private fun animateDice() {
        // Animer les dés (exemple : translation de bas en haut)
        val textView1: TextView = findViewById(R.id.textView1)
        val textView2: TextView = findViewById(R.id.textView2)

        textView1.animate().translationYBy(-100f).setDuration(300).withEndAction {
            textView1.animate().translationYBy(100f).setDuration(300)
        }.start()

        textView2.animate().translationYBy(-100f).setDuration(300).withEndAction {
            textView2.animate().translationYBy(100f).setDuration(300)
        }.start()

    }
}

/**
 * Définition de la classe Dice
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
