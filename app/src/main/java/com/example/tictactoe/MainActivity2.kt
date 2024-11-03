package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var buttons: Array<Array<Button>>
    private var playerTurn = true
    private var roundCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        buttons = arrayOf(
            arrayOf(binding.button1, binding.button2, binding.button3),
            arrayOf(binding.button4, binding.button5, binding.button6),
            arrayOf(binding.button7, binding.button8, binding.button9)
        )

        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener {
                    onButtonClick(buttons[i][j])
                }
            }
        }

        binding.exitbutton.setOnClickListener {
            finish()
        }
    }

    private fun onButtonClick(button: Button) {
        if (button.text != "") {
            return
        }


        button.text = if (playerTurn) "X" else "O"
        roundCount++

        if (checkForWin()) {
            Toast.makeText(this, if (playerTurn) "X wins!" else "O wins!", Toast.LENGTH_SHORT).show()
            resetBoard()
        } else if (roundCount == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
            resetBoard()
        } else {
            playerTurn = !playerTurn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j].text.toString()
            }
        }


        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != "") {
            return true
        }
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != "") {
            return true
        }
        return false
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
            }
        }
        roundCount = 0
        playerTurn = true
    }

}
