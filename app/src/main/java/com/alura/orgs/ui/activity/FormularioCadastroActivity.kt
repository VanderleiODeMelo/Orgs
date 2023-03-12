package com.alura.orgs.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alura.orgs.dao.Dao
import com.alura.orgs.databinding.ActivityFormularioCadastroBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.ui.activity.carregarImagem.tentaCarregarImagemView
import com.alura.orgs.ui.activity.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioCadastroActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFormularioCadastroBinding.inflate(layoutInflater)
    }
    var url: String? = null
    private var produtoId: Long = 0L
    private val dao = Dao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Formulário cadastro"
        configurarBotaoSalvar()
        binding.formularioCadastroImagemView.setOnClickListener(View.OnClickListener {


                FormularioImagemDialog(this).mostra(url) { imagem ->

                    url = imagem
                    binding.formularioCadastroImagemView.tentaCarregarImagemView(url)
                }


        })
    }


    private fun configurarBotaoSalvar() {
        binding.formularioCadastroBotaoSalvar.setOnClickListener(View.OnClickListener {


            val titulo = binding.textInputEditTextCampoUrl.text.toString()
            val decricao = binding.textInputEditTextCampoDescricao.text.toString()
            val valorEmTexto = binding.textInputEditTextCampoValor.text.toString()
            val data = binding.textInputEditTextCampoData.text.toString()

            val valor = if (valorEmTexto.isBlank()) {

                BigDecimal.ZERO
            } else {
                BigDecimal(valorEmTexto)
            }

            val produto = criarProduto(titulo, decricao, valor, url)
            dao.salvar(produto).apply { finish() }
        })
    }

    private fun criarProduto(
        titulo: String,
        decricao: String,
        valor: BigDecimal, url: String?
    ) = Produto(
        produtoId,
        titulo = titulo,
        descricao = decricao,
        valor = valor,
        imagem = url
    )
}