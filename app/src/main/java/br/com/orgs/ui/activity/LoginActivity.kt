package br.com.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityAutenticacaoBinding
import br.com.orgs.extensions.toast
import br.com.orgs.extensions.vaiPara
import br.com.orgs.preferences.dataStore
import br.com.orgs.preferences.usuarioLogadoPreferences
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAutenticacaoBinding.inflate(layoutInflater)
    }
    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
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
            autentica(usuario, senha)
        }
    }

    private fun autentica(usuario: String, senha: String) {
        lifecycleScope.launch {
            usuarioDao.autentica(usuario, senha)?.let { usuario ->
                dataStore.edit { preferences ->
                    preferences[usuarioLogadoPreferences] = usuario.id
                }
                vaiPara(ListaProdutos::class.java)
                finish()
            } ?: toast("Falha na Autenticação")
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.botaoCadastrar.setOnClickListener {
            Intent(this, ActivityCadastro::class.java)
                .apply {
                    startActivity(this)
                }
        }
    }
}