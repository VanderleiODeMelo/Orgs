package com.alura.orgs.dao

import com.alura.orgs.model.Produto
import java.math.BigDecimal

class Dao {

  companion object{

      private val listaProdutos = mutableListOf<Produto>()
  }


    fun salvar(produto: Produto) {


        listaProdutos.add(produto)
    }

    fun buscaTodosProdutos(): List<Produto> {

        return listaProdutos.toList()
    }

    fun remover(produto: Produto) {

        listaProdutos.remove(produto)
    }


}