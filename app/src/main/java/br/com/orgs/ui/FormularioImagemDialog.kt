package br.com.orgs.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.orgs.databinding.FormularioImagemBinding
import br.com.orgs.extensions.carregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        val binding = FormularioImagemBinding
                .inflate(LayoutInflater.from(context)).apply {

                urlPadrao.let {
                    imagemFormulario.carregarImagem(it)
                    FormularioImagemURL.setText(it)
                }

                botaoFormularioImagem.setOnClickListener {
                    val url = FormularioImagemURL.text.toString()
                    imagemFormulario.carregarImagem(url)
                }


                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("confirmar") { _, _ ->
                        val url = FormularioImagemURL.text.toString()
                        quandoImagemCarregada(url)
                    }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()
            }
    }

}