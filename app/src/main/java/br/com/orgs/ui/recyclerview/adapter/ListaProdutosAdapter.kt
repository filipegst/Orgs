package br.com.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.databinding.ProductItemBinding
import br.com.orgs.extensions.carregarImagem
import br.com.orgs.extensions.fornataParaReal
import br.com.orgs.model.Produtos

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produtos>,
    var clicaNoItemListener: (produto: Produtos) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private lateinit var produto: Produtos

        init {
            itemView.setOnClickListener{
                if (::produto.isInitialized) {
                    clicaNoItemListener(produto)
                }
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






