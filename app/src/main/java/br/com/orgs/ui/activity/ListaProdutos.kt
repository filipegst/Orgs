package br.com.orgs.ui.activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.R
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityMainBinding
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

private const val TAG = "detalhesproduto"


class ListaProdutos: AppCompatActivity() {

    val adapter = ListaProdutosAdapter(this)

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
          configuraReciclerView()
        setContentView(binding.root)
          configuraFab()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
            super.onResume()
        val db =AppDatabase.instancia(this)
        val produtoDao= db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())

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
        adapter.clicaNoItemListener ={
            val intent = Intent(this, DetalheActivity::class.java).apply {
                putExtra(CHAVE_ID,it.id)
            }
            startActivity(intent)
        }
    }
}