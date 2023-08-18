package br.com.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ProductItemBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.extensions.fornataParaReal
import br.com.orgs.model.Produtos

private const val TAG = "detalheProduto"


class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produtos> = emptyList(),

    var clicaNoItemListener: (produto: Produtos) -> Unit = {},
    var clicaNoEdit:(produto: Produtos) -> Unit = {},
    var clicaNoDelete:(produto: Produtos) -> Unit = {}

) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    val produtos = produtos.toMutableList()

    val produtoDao by lazy {
        AppDatabase.instancia(context).produtoDao()
    }
    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener


    {



        private lateinit var produto: Produtos


        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    clicaNoItemListener(produto)
                }
            }
            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(R.menu.popup_menu, menu)

                    setOnMenuItemClickListener (this@ViewHolder)
                }.show()
                true
            }
        }

        fun vincula(produto: Produtos) {
            this.produto = produto
            val nome = binding.productItemNome
            nome.text = produto.Nome
            val descricao = binding.productItemDescricao
            descricao.text = produto.Descricao
            val valor = binding.productItemValor
            valor.text =produto.Valor.fornataParaReal()
            binding.imageView.carregarImagem(produto.imagem)
            val visibilidade = if (produto.imagem != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (it.itemId) {
                    R.id.menu_editar -> {
                        clicaNoEdit(produto)
                    }
                    R.id.menu_delete -> {
                        clicaNoDelete(produto)
                    }
                }
            }
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)

    }

    override fun getItemCount(): Int = produtos.size
    fun atualiza(produtos: List<Produtos>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}






