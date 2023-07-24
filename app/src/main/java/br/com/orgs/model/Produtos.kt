package br.com.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Entity
 @Parcelize
data class Produtos (
    @PrimaryKey (autoGenerate = true) val id : Long = 0L,
    val Nome : String,
    val Descricao: String,
    val Valor : BigDecimal,
    val imagem : String? = null

) : Parcelable