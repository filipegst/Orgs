package br.com.orgs.ui.activity
import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.model.Produtos
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal


class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        setContentView(R.layout.activity_main)
//        val nome = findViewById<TextView>(R.id.nome)
//            nome.text = "Cesta de frutas"
//        val descricao = findViewById<TextView>(R.id.descricao)
//            descricao.text = "banana, maça, uva, laranja"
//        val valor = findViewById<TextView>(R.id.valor)
//            valor.text = "R$25,00"

         val reciclerView = findViewById<RecyclerView>(R.id.recycler)
        reciclerView.adapter = ListaProdutosAdapter (this, listOf(
            Produtos("teste", "descrição", BigDecimal("19.99")),
            Produtos("teste2","outra desciçao", BigDecimal("30.00"))
        ))
        reciclerView.layoutManager = LinearLayoutManager(this)

    }

}