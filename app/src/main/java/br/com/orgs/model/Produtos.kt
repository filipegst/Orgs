package br.com.orgs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal


 @Parcelize
data class Produtos (

    val Nome : String,
    val Descricao: String,
    val Valor : BigDecimal,
    val imagem : String? = null

) : Parcelable