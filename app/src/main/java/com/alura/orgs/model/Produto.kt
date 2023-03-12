package com.alura.orgs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
class Produto(

    val id: Long = 0L,
    val titulo: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null
) : Parcelable {
}