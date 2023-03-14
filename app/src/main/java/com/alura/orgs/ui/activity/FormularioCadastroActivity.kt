package com.alura.orgs.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alura.orgs.database.OrgsDataBase
import com.alura.orgs.databinding.ActivityFormularioCadastroBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.ui.activity.carregarImagem.tentaCarregarImagemView
import com.alura.orgs.ui.activity.constantes.CHAVE_PRODUTO_ID
import com.alura.orgs.ui.activity.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioCadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {

        OrgsDataBase.instance(this).produtoDao()
    }
    var url: String? = null
    private var produtoId: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Formulário cadastro"
        configurarBotaoSalvar()
        listenerCarregarImagemDialog()
        idProduto()


    }

    override fun onResume() {
        super.onResume()
        tentaBuscarProduto()

    }

    private fun tentaBuscarProduto() {
        val produto = produtoDao.buscaPorId(produtoId)

        produto?.let {
            preencheCampos(it)
        }
    }

    private fun idProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produto: Produto) {

        produtoId = produto.id
        title = "Altera produto"
        url = produto.imagem
        binding.formularioCadastroImagemView.tentaCarregarImagemView(produto.imagem)
        binding.textInputEditTextCampoUrl.setText(produto.titulo)
        binding.textInputEditTextCampoDescricao.setText(produto.descricao)
        binding.textInputEditTextCampoValor.setText(produto.valor.toPlainString())
    }

    private fun listenerCarregarImagemDialog() {
        binding.formularioCadastroImagemView.setOnClickListener(View.OnClickListener {


            FormularioImagemDialog(this).mostra(url) { imagem ->

                url = imagem
                binding.formularioCadastroImagemView.tentaCarregarImagemView(url)
            }
        })
    }

    private fun configurarBotaoSalvar() {

        val botaoSalvar = binding.formularioCadastroBotaoSalvar

        botaoSalvar.setOnClickListener(View.OnClickListener {

            binding.detalheProdutoContentLoadingProgressBar.show()//vai aparecer

            val produto = criarProduto()
            produtoDao.salvar(produto)

            binding.detalheProdutoContentLoadingProgressBar.hide()//vai esconder
            
            finish()
        })


    }

    private fun criarProduto(): Produto {

        val titulo = binding.textInputEditTextCampoUrl.text.toString()
        val decricao = binding.textInputEditTextCampoDescricao.text.toString()
        val valorEmTexto = binding.textInputEditTextCampoValor.text.toString()
        val data = binding.textInputEditTextCampoData.text.toString()

        val valor = if (valorEmTexto.isBlank()) {

            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }
        return Produto(produtoId, titulo = titulo, descricao = decricao, valor = valor, url)

    }

}