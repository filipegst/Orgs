package br.com.orgs.ui.activity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.dao.ProdutosDao
import br.com.orgs.databinding.ActivityFormularioBinding
import br.com.orgs.databinding.FormularioImagemBinding
import br.com.orgs.model.Produtos
import coil.load
import java.math.BigDecimal

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }

    private var url:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioImagem.setOnClickListener {
            val bindingImagemFormulario = FormularioImagemBinding.inflate(layoutInflater)
                bindingImagemFormulario.botaoFormularioImagem.setOnClickListener{
                   val url =  bindingImagemFormulario.FormularioImagemURL.text.toString()
                    bindingImagemFormulario.imagemFormulario.load(url)
                }
           AlertDialog.Builder(this)
                .setView(bindingImagemFormulario.root)
                .setPositiveButton("confirmar") { _, _ ->
                    url =  bindingImagemFormulario.FormularioImagemURL.text.toString()
                    binding.activityFormularioImagem.load(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener{
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produtos {
        val campoNome = binding.activityNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produtos(Nome = nome, Descricao = descricao, Valor = valor, imagem = url)
    }

}