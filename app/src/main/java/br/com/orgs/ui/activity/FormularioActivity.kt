package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.dao.ProdutosDao
import br.com.orgs.databinding.ActivityFormularioBinding
import br.com.orgs.model.Produtos
import java.math.BigDecimal

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        botaoSalvar.setOnClickListener(criaProduto())
    }

    private fun criaProduto(): (View) -> Unit = {
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

        val produtoNovo = Produtos(nome, descricao, valor)
        Log.i("formularioProduto", "onCreate:$produtoNovo")
        val dao = ProdutosDao()
        ProdutosDao().adiciona(produtoNovo)
        Log.i("formularioProduto", "onCreate:${dao.buscaProdutos()}")
        finish()
    }

}