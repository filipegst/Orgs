package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import br.com.orgs.database.AppDatabase
import br.com.orgs.database.ProdutoDao
import br.com.orgs.databinding.ActivityFormularioBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.model.Produtos
import kotlinx.coroutines.flow.collect
import br.com.orgs.ui.FormularioImagemDialog
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FormularioActivity : UsuarioBaseActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }
    private val produtoDao: ProdutoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }


    private var url: String? = null
    private var produtoId = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Produto"
        configuraBotaoSalvar()
        binding.activityFormularioImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioImagem.carregarImagem(url)
                }
        }
        tentaCarregarProduto()
        lifecycleScope.launch {
            usuario
                .filterNotNull()
                .collect {
                    Log.i("FormularioProduto", "onCreate: $it")
                }
        }
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarProduto()
    }

    private fun tentaBuscarProduto() {
        lifecycleScope.launch {
            produtoDao.buscaID(produtoId).collect {
                it?.let { produtoEncontrado ->
                    title = "Alterar produto"
                    preencheCampos(produtoEncontrado)
                }
            }
        }
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_ID, 0L)
    }

    private fun preencheCampos(produto: Produtos) {
        url = produto.imagem
        binding.activityFormularioImagem.carregarImagem(produto.imagem)
        binding.activityNome.setText(produto.Nome)
        binding.activityDescricao.setText(produto.Descricao)
        binding.activityValor.setText(produto.Valor.toPlainString())
    }


    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        botaoSalvar.setOnClickListener {

            lifecycleScope.launch {
                usuario.firstOrNull()?.let { usuario ->
                    val produtoNovo = criaProduto(usuario.id)
                    produtoDao.salva(produtoNovo)
                    finish()
                }
            }
        }
    }


    private fun criaProduto(usuarioid:String): Produtos {
        val campoNome = binding.activityNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produtos(
            id = produtoId,
            Nome = nome,
            Descricao = descricao,
            Valor = valor,
            imagem = url,
            usuarioid = usuarioid
        )
    }

}
