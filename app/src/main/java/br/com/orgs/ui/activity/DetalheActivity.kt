package br.com.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.R
import br.com.orgs.databinding.ActivityDetalhesBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.extensions.fornataParaReal
import br.com.orgs.model.Produtos

class DetalheActivity: AppCompatActivity(R.layout.activity_detalhes) {
    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        carregaProduto()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun carregaProduto() {

        intent.getParcelableExtra("Produtos",Produtos::class.java)?.let { produtoCaregado ->
            prencherDetalhes(produtoCaregado)
        }
    }


    private fun prencherDetalhes(produtoCaregado: Produtos) {
        with(binding) {
            activityDetalheImagem.carregarImagem(produtoCaregado.imagem)
            detalheTitulo.text= produtoCaregado.Nome
            detalheDescricao.text = produtoCaregado.Descricao
            detalheValor.text = produtoCaregado.Valor.fornataParaReal()
        }
    }

}
