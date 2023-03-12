package com.alura.orgs.model

import java.math.BigDecimal
import java.util.*

class Produto(

     val id: Long = 0L,
     val titulo: String,
     val descricao: String,
     val valor: BigDecimal,
     val imagem: String? = null
) {
}