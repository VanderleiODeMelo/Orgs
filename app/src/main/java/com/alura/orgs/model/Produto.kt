package com.alura.orgs.model

import java.math.BigDecimal
import java.util.*

class Produto(

    private val id: Long = 0L,
    private val titulo: String,
    private val descricao: String,
    private val valor: BigDecimal,
    private val data: Calendar,
    private val imagem: String? = null
) {
}