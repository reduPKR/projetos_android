package br.com.orgs.dao

import br.com.orgs.models.Produto

class ProdutoDAO {

    fun adicionar(produto: Produto){
        produtos.add(produto)
    }

    fun buscarTodos(): List<Produto>{
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}