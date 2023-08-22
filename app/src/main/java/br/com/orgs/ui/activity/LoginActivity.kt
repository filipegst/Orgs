package br.com.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.databinding.ActivityAutenticacaoBinding

class LoginActivity: AppCompatActivity() {
    private val binding by lazy {
        ActivityAutenticacaoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
        setContentView(binding.root)

    }

    private fun configuraBotaoEntrar() {
        binding.botaoEntrar.setOnClickListener {
            val usuario = binding.activityUsuario.text.toString()
            val senha = binding.activitySenha.text.toString()
           Intent(this, ListaProdutos::class.java)
                .apply {
                    startActivity(this)
                }
        }
    }

    private fun configuraBotaoCadastrar () {
        binding.botaoCadastrar.setOnClickListener {
        Intent(this, ActivityCadastro::class.java)
                .apply {
                    startActivity(this) }
        }
    }
}