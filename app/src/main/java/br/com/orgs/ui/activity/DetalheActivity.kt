package br.com.orgs.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.orgs.R
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityDetalhesBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.extensions.fornataParaReal
import br.com.orgs.model.Produtos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetalheActivity : AppCompatActivity(R.layout.activity_detalhes) {
    private var produtoId: Long = 0L
    private  var produto: Produtos? = null
    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }
    val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
        }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhe, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onResume() {
        super.onResume()
        BuscaProduto()
    }

    private fun BuscaProduto() {
        lifecycleScope.launch {
            produtoDao.buscaID(produtoId).collect { produtoEncontrado ->
                produto = produtoEncontrado
                produto?.let {
                    prencherDetalhes(it)
                } ?: finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_delete -> {
                    lifecycleScope.launch {
                            produto?.let {
                                produtoDao.remove(it)
                                finish()
                            }
                        }
                }


                R.id.menu_editar -> {
                    Intent (this,FormularioActivity::class.java).apply {
                        putExtra(CHAVE_ID,produtoId)
                        startActivity(this)
                    }
                }
            }
        return super.onOptionsItemSelected(item)
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        carregaProduto()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun carregaProduto() {
                produtoId = intent.getLongExtra(CHAVE_ID, 0L)
            }

    private fun prencherDetalhes(produtoCaregado: Produtos) {
        lifecycleScope.launch{
                with(binding) {
                    activityDetalheImagem.carregarImagem(produtoCaregado.imagem)
                    detalheTitulo.text = produtoCaregado.Nome
                    detalheDescricao.text = produtoCaregado.Descricao
                    detalheValor.text = produtoCaregado.Valor.fornataParaReal()
                }
            }
        }
    }


