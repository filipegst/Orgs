package br.com.orgs.ui.activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.dao.ProdutosDao
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity: AppCompatActivity(R.layout.activity_main) {

    val dao = ProdutosDao()
    val adapter = ListaProdutosAdapter(this, dao.buscaProdutos())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
          configuraReciclerView()
          configuraFab()

    }

    override fun onResume() {
            super.onResume()
            adapter.atualiza(dao.buscaProdutos())

    }

    private fun configuraFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraReciclerView() {
        val reciclerView = findViewById<RecyclerView>(R.id.recycler)
        reciclerView.adapter = adapter
    }
}