package br.com.orgs.extensions

import android.widget.ImageView
import coil.load

fun ImageView.carregarImagem(url:String? = null) {
    load(url) {
        fallback(br.com.orgs.R.drawable.erro)
        error(br.com.orgs.R.drawable.erro)
        placeholder(br.com.orgs.R.drawable.loading)
    }
}