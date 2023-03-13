package com.alura.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
@Entity
class Produto(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val titulo: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null
) : Parcelable {
}