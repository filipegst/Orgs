package br.com.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.databinding.ActivityCadastroBinding
import br.com.orgs.model.Usuario

class ActivityCadastro : AppCompatActivity () {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun configuraBotaoCadastrar (){
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener{
            val novoUsuario = criaUsuario()
            finish()
        }
    }

    private fun criaUsuario(): Usuario {
        val usuario = binding.activityUsuario.text.toString()
        val senha = binding.activitySenha.text.toString()
        return Usuario(senha, usuario)

    }
}