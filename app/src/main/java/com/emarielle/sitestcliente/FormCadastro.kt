package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.emarielle.sitestcliente.databinding.ActivityFormCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")


        binding.btnCadastrar.setOnClickListener { view ->
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val confirmarSenha = binding.editConfirmaSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            } else if (senha != confirmarSenha) {
                val snackbar = Snackbar.make(view, "As senhas não coincidem!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            } else {
                if (senha.length < 6) {
                    val snackbar = Snackbar.make( view,
                        "Digite uma senha com no mínimo 6 caracteres!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                } else {
                    auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { cadastro ->
                            if (cadastro.isSuccessful) {
                                val snackbar = Snackbar.make(view,
                                    "Cadastro feito com sucesso!",
                                    Snackbar.LENGTH_SHORT
                                )
                                snackbar.setBackgroundTint(Color.BLUE)
                                snackbar.setTextColor(Color.WHITE)
                                snackbar.show()
                                binding.editEmail.setText("")
                                binding.editSenha.setText("")
                                binding.editConfirmaSenha.setText("")



                            }

                        }.addOnFailureListener { exception ->
                            val mensagemErro = when (exception) {
                                is FirebaseAuthWeakPasswordException -> "Digite uma senha com no mínimo 6 caracteres!"
                                is FirebaseAuthInvalidCredentialsException -> "Digite um email válido"
                                is FirebaseAuthUserCollisionException -> "Esta conta já foi cadastrada!"
                                is FirebaseNetworkException -> "Sem conexão com a internet!"
                                else -> "Erro ao cadastrar usuário!"
                            }
                            val snackbar = Snackbar.make(view, mensagemErro, Snackbar.LENGTH_SHORT)
                            snackbar.setBackgroundTint(Color.RED)
                            snackbar.setTextColor(Color.WHITE)
                            snackbar.show()
                        }

                }
            }

            // Adiciona um atraso antes de chamar telaLogin()
            Handler().postDelayed({
                telaLogin()
            }, 2000) // 2000 milissegundos (2 segundos)

        }

    }

    private fun telaLogin() {
        val telalogin = Intent(this, FormLogin::class.java)
        startActivity(telalogin)
    }
}