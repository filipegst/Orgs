package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityCadastroBinding
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
            Log.i("CadastroUsuario","onCreate $novoUsuario")
            lifecycleScope.launch {
                try {
                    dao.salva(novoUsuario)
                    finish()
                } catch (e: Exception) {
                    Log.e("CadastroUsuario","ConfiguraBotaoCadastrar",e)
                    Toast.makeText(
                        this@ActivityCadastro,
                        "Falha ao Cadastar Usuario",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun criaUsuario(): Usuario {
        val usuario = binding.activityUsuario.text.toString()
        val senha = binding.activitySenha.text.toString()
        return Usuario(usuario,senha)

    }
}