package br.com.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityFormularioBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.model.Produtos
import br.com.orgs.ui.FormularioImagemDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }
    val produtoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }


    private var url: String? = null
    var produtoId = 0L
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
            produtoId = intent.getLongExtra(CHAVE_ID,0L)
        }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch  {
                produtoDao.buscaID(produtoId)?.let {
                        title = "Editar Produto"
                        preencheCampos(it) }
            }
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
                val produtoNovo = criaProduto()
             produtoDao.salva(produtoNovo)
                finish()
                     }
            }
        }



    private fun criaProduto(): Produtos {
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

        return Produtos(id=produtoId, Nome = nome, Descricao = descricao, Valor = valor, imagem = url)
    }

}
