package com.alura.orgs.conversores

import androidx.room.TypeConverter
import java.math.BigDecimal

class Conversores {

    //de BigDecimal para Double enviar para banco
    @TypeConverter
    fun paraDouble(valor: BigDecimal?): Double? {

        return valor?.let {
            valor.toDouble()
        }
    }

    //de Double para BigDecimal
    @TypeConverter
    fun deDoubleParaBigDecimal(valor: Double?): BigDecimal {

        return if (valor != null) {
            BigDecimal(valor)

        } else {

            BigDecimal.ZERO
        }
    }
}
