package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityCadastroBinding
import br.com.orgs.extensions.toast
import br.com.orgs.model.Usuario
import kotlinx.coroutines.launch

class ActivityCadastro : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoCadastrar()
        setContentView(binding.root)

    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            val novoUsuario = criaUsuario()
            cadastra(novoUsuario)
        }
    }

    private fun cadastra(novoUsuario: Usuario) {
        lifecycleScope.launch {
            try {
                dao.salva(novoUsuario)
                finish()
            } catch (e: Exception) {
                Log.e("CadastroUsuario", "ConfiguraBotaoCadastrar", e)
                toast("Falha ao Cadastar Usuario")
            }
        }
    }

    private fun criaUsuario(): Usuario {
        val id = binding.activityUsuario.text.toString()
        val senha = binding.activitySenha.text.toString()
        return Usuario(id,senha)

    }
}