package br.com.orgs.dao

import br.com.orgs.model.Produtos

class ProdutosDao {

    fun adiciona (produto: Produtos) {
        produtos.add(produto)
    }

    fun buscaProdutos(): List<Produtos> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produtos>()
    }
}