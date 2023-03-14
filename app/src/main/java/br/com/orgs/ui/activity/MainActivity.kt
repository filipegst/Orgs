package br.com.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter


class MainActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        setContentView(R.layout.product_item)
//        val nome = findViewById<TextView>(R.id.nome)
//            nome.text = "Cesta de frutas"
//        val descricao = findViewById<TextView>(R.id.descricao)
//            descricao.text = "banana, maça, uva, laranja"
//        val valor = findViewById<TextView>(R.id.valor)
//            valor.text = "R$25,00"

         val reciclerView = findViewById<RecyclerView>(R.id.recyclerView)
        reciclerView.adapter = ListaProdutosAdapter ()
    }

}