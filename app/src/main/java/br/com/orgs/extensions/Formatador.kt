package br.com.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.fornataParaReal(): String {
    val formatador = NumberFormat.getCurrencyInstance(Locale("pr", "br"))
    return formatador.format(this)
}