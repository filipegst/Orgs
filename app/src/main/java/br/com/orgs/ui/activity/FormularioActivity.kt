package br.com.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.dao.ProdutosDao
import br.com.orgs.databinding.ActivityFormularioBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.model.Produtos
import br.com.orgs.ui.FormularioImagemDialog
import java.math.BigDecimal

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }

    private var url: String? = null
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
        }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val dao = ProdutosDao()
            botaoSalvar.setOnClickListener {
                val produtoNovo = criaProduto()
                dao.adiciona(produtoNovo)
                finish()
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

        return Produtos(Nome = nome, Descricao = descricao, Valor = valor, imagem = url)
    }

}
