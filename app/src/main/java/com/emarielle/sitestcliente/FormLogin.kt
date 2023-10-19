package com.emarielle.sitestcliente

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emarielle.sitestcliente.databinding.ActivityFormLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#6746C5")

        binding.btnEntrar.setOnClickListener { view ->
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.rgb(255, 105, 105))
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            } else {
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { autenticacao ->
                    if (autenticacao.isSuccessful) {
                        // Usuário autenticado com sucesso, entre no sistema
                        entrarNoSistema()
                    } else {
                        // Tratamento de erro ao fazer login
                        val snackbar = Snackbar.make(view, "Erro ao Fazer Login", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.setTextColor(Color.WHITE)
                        snackbar.show()
                    }
                }.addOnFailureListener {
                    // Tratamento de falha na autenticação
                    val snackbar = Snackbar.make(view, "Erro ao Fazer Login", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                }
            }
        }

        binding.txtCadastreSe.setOnClickListener{
            telaCadastro()
        }

        binding.txtRecuperaSenha.setOnClickListener {view ->
            val email = binding.editEmail.text.toString()
            if (email.isNotEmpty()) {
                enviarEmailRedefinicao(email)
            } else {
                val snackbar = Snackbar.make(view, "Insisra seu e-mail!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.rgb(255,105,105))
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }
        }


    }


    private fun entrarNoSistema(){
        val telabemvindo = Intent(this,TelaBemVindo::class.java)
        startActivity(telabemvindo)
        finish()
    }

    private fun telaCadastro(){
        val TelaCadastro = Intent(this,FormCadastro::class.java)
        startActivity(TelaCadastro)
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual!= null){
            entrarNoSistema()
        }
    }

    private fun enviarEmailRedefinicao(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Email de redefinição enviado com sucesso
                    val snackbar = Snackbar.make(
                        findViewById(android.R.id.content),
                        "Um e-mail de redefinição foi enviado para o seu endereço de e-mail.",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.show()
                } else {
                    // Falha ao enviar o email de redefinição
                    val snackbar = Snackbar.make(
                        findViewById(android.R.id.content),
                        "Falha ao enviar o e-mail de redefinição. Verifique o endereço de e-mail.",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.show()
                }
            }
    }

}