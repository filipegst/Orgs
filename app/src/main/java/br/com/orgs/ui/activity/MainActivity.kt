package br.com.orgs.ui.activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.dao.ProdutosDao
import br.com.orgs.databinding.ActivityMainBinding
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter


class MainActivity: AppCompatActivity() {

    val dao = ProdutosDao()
    val adapter = ListaProdutosAdapter(this, dao.buscaProdutos())

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
          configuraReciclerView()
        setContentView(binding.root)
          configuraFab()



    }

    override fun onResume() {
            super.onResume()
            adapter.atualiza(dao.buscaProdutos())

    }

    private fun configuraFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraReciclerView() {
        val reciclerView = binding.recycler
        reciclerView.adapter = adapter
    }
}