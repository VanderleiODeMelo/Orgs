package com.alura.orgs.ui.adapter.recyclerview

import com.alura.orgs.model.Produto

interface ClickProdutoListener {

    fun onItemClickListener(produto: Produto,posicao:Int)
}