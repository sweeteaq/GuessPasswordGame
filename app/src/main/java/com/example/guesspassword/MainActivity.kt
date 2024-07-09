package com.example.guesspassword

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game()

        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val buttonGuess: Button = findViewById(R.id.buttonGuess)
        val textViewResult: TextView = findViewById(R.id.textViewResult)
        val buttonShowPassword: Button = findViewById(R.id.buttonShowPassword)
        val textViewPassword: TextView = findViewById(R.id.textViewPassword)
        val buttonNewPassword: Button = findViewById(R.id.buttonNewPassword)
        val textViewHint: TextView = findViewById(R.id.textViewHint)

        buttonGuess.setOnClickListener {
            val userGuess = editTextPassword.text.toString()

            if (game.guess(userGuess)) {
                textViewResult.text = "Поздравляем! Вы угадали пароль с ${game.attempts} попытки."
                buttonShowPassword.visibility = Button.GONE
                buttonNewPassword.visibility = Button.VISIBLE
                textViewHint.text = ""
            } else {
                textViewResult.text = "Неверный пароль. Попробуйте еще раз."
                textViewHint.text = game.getHint(userGuess)
                if (game.attempts >= 3) {
                    buttonShowPassword.visibility = Button.VISIBLE
                }
            }
        }

        buttonShowPassword.setOnClickListener {
            textViewPassword.text = "Загаданный пароль: ${game.password}"
        }

        buttonNewPassword.setOnClickListener {
            game.reset()
            editTextPassword.text.clear()
            textViewResult.text = ""
            textViewPassword.text = ""
            textViewHint.text = ""
            textViewResult.text = "Пароль сброшен. Начните угадывать новый пароль."
            buttonNewPassword.visibility = Button.GONE
        }
    }
}
