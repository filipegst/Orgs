package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.R
import br.com.orgs.dao.ProdutosDao
import br.com.orgs.model.Produtos
import java.math.BigDecimal

class FormularioActivity : AppCompatActivity(R.layout.activity_formulario) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.botaoSalvar)
        botaoSalvar.setOnClickListener(criaProduto())
    }

    private fun criaProduto(): (View) -> Unit = {
        val campoNome = findViewById<TextView>(R.id.activityNome)
        val nome = campoNome.text.toString()
        val campoDescricao = findViewById<TextView>(R.id.activityDescricao)
        val descricao = campoDescricao.text.toString()
        val campoValor = findViewById<TextView>(R.id.activityValor)
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