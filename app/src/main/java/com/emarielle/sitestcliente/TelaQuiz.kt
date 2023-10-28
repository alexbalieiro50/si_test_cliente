package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import com.emarielle.sitestcliente.databinding.ActivityTelaPrincipalBinding
import com.emarielle.sitestcliente.databinding.ActivityTelaQuizBinding
import com.emarielle.sitestcliente.perguntas.Questions

class TelaQuiz : AppCompatActivity() {

    private lateinit var binding: ActivityTelaQuizBinding
    private var currentQuestionIndex: Int = 0
    private var score: Int = 0

    private val perguntas = listOf(
        Questions("Qual dos seguintes métodos é usado para verificar a identidade de um usuário em um sistema de computador?",
            listOf("Senhas", "Cookies", "Firewalls", "Protocolos de rede Resposta"), "Firewalls"),

        Questions("Quem escreveu 'Romeu e Julieta'?",
            listOf("Shakespeare", "Hemingway", "Tolkien"), "Shakespeare")
    )
    private val totalQuestions = perguntas.size // Mova esta linha para cá

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTelaQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")


        showQuestion()
    }

    private fun showQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            val question = perguntas[currentQuestionIndex]
            binding.txtPergunta.text = question.questionText

            // Limpe as opções de resposta anteriores
            binding.groupRadio.removeAllViews()

            for (option in question.options) {
                val radioButton = RadioButton(this)
                radioButton.text = option
                radioButton.textSize = 20f
                radioButton.setTextColor(Color.parseColor("#6746C5"))
                radioButton.setBackgroundResource(R.drawable.bg_radio_button)
                radioButton.buttonDrawable = null
                radioButton.setPadding(25, 25, 25, 25)

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                // Define a margem inferior em pixels
                layoutParams.bottomMargin = resources.getDimensionPixelSize(com.google.android.material.R.dimen.abc_action_bar_subtitle_bottom_margin_material)

                radioButton.layoutParams = layoutParams
                binding.groupRadio.addView(radioButton)
            }



            // Atualize o contador de perguntas
            val questionCounterText = "Pergunta ${currentQuestionIndex + 1}/$totalQuestions"
            binding.txtTituloPergunta.text = questionCounterText

            // Defina um ouvinte para o botão "Próxima pergunta"
            binding.btnResponder.setOnClickListener {
                checkAnswer(question)
                currentQuestionIndex++
                if (currentQuestionIndex < totalQuestions) {
                    showQuestion()
                } else {
                    showResults()
                }
            }
        } else {
            showResults()
        }
    }

    private fun checkAnswer(question: Questions) {
        val selectedRadioButtonId = binding.groupRadio.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val selectedAnswer = selectedRadioButton.text.toString()

            if (selectedAnswer == question.correctAnswer) {
                score++
            }
        }
    }

    private fun showResults() {
        val resultText = "Pontuação: $score de $totalQuestions"

        // Criar um Intent para abrir a atividade de resultados
        val intent = Intent(this, TelaResultadoQuiz::class.java)
        intent.putExtra("pontuacao", score)
        intent.putExtra("totalPerguntas", totalQuestions)
        startActivity(intent)
        finish() // Finalize a ativade atual para que o usuário não possa voltar
    }





}