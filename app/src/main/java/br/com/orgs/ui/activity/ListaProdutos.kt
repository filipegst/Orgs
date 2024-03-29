package br.com.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.orgs.R
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityMainBinding
import br.com.orgs.extensions.vaiPara
import br.com.orgs.model.Produtos
import br.com.orgs.preferences.dataStore
import br.com.orgs.preferences.usuarioLogadoPreferences
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

private const val TAG = "detalhesproduto"


class ListaProdutos : UsuarioBaseActivity() {

    val adapter = ListaProdutosAdapter(this)

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraReciclerView()
        setContentView(binding.root)
        configuraFab()
        lifecycleScope.launch {
            usuario.filterNotNull().collect {usuario ->
                buscaProdutoUsuario(usuario.id)
            }
        }
    }


    private suspend fun buscaProdutoUsuario(usuarioid: String) {
        produtoDao.buscaTodosDoUsuario(usuarioid).collect { produtos ->
            adapter.atualiza(produtos)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            val produtoOrdenado: Any? = when (item.itemId) {
                R.id.menuNomeAsc ->
                    produtoDao.buscaNomeAsc()

                R.id.menuNomeDesc ->
                    produtoDao.buscaNomeDesc()

                R.id.menuDescricaoAsc ->
                    produtoDao.buscaDescricaoAsc()

                R.id.menuDescricaoDesc ->
                    produtoDao.buscaDescricaoDesc()

                R.id.menuValorAsc ->
                    produtoDao.buscaValorAsc()

                R.id.menuValorDesc ->
                    produtoDao.buscaValorDesc()

                R.id.semOrdem ->
                    produtoDao.semOrdem()


                else -> null
            }
            produtoOrdenado?.let {
                adapter.atualiza(it as List<Produtos>)
            }

            lifecycleScope.launch {
                when (item.itemId) {
                    R.id.menu_logout -> {
                        deslogaUsuario()

                    }
                }
            }
        }

    return super.onOptionsItemSelected(item)
}

private fun configuraFab() {
    val fab = binding.floatingActionButton
    fab.setOnClickListener {
        val intent = Intent(this, FormularioActivity::class.java)
        startActivity(intent)
    }
}

private fun configuraReciclerView() {
    val reciclerView = binding.recycler
    reciclerView.adapter = adapter
    adapter.clicaNoItemListener = {
        val intent = Intent(this, DetalheActivity::class.java).apply {
            putExtra(CHAVE_ID, it.id)
        }
        startActivity(intent)
    }
}
}