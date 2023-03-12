package com.alura.orgs.util

import com.alura.orgs.model.Produto
import java.text.NumberFormat
import java.util.*


class MoedaUtil {


    companion object{


        fun formatarMoedaBrasileira(produto: Produto): String {
            val format = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorFormatado = format.format(produto.valor)
            return valorFormatado
        }
    }

}