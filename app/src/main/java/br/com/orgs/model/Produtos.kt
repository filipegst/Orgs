package br.com.orgs.model

import java.math.BigDecimal

data class Produtos (
    val Nome : String,
    val Descricao: String,
    val Valor : BigDecimal,
    val imagem : String? = null


        )
