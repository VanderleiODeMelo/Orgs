package com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alura.orgs.R
import com.alura.orgs.database.OrgsDataBase
import com.alura.orgs.databinding.ActivityDetalheProdutoBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.ui.activity.carregarImagem.tentaCarregarImagemView
import com.alura.orgs.ui.activity.constantes.CHAVE_PRODUTO_ID
import com.alura.orgs.util.MoedaUtil

class DetalheProdutoActivity : AppCompatActivity() {


    private var produtoId: Long = 0L
    private var produto: Produto? = null
    private val binding by lazy {

        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {

        OrgsDataBase.instance(this).produtoDao()
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Detalhes"

        tentaCarregarProdutos()

    }

    override fun onResume() {
        super.onResume()


        buscaProduto()

    }

    private fun buscaProduto() {
        produto = produtoDao.buscaPorId(produtoId)
        produto?.let {
            preencherCampos(it)
        } ?: finish()
    }
    private fun tentaCarregarProdutos() {

        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menus_opcoes_detalhe_produto, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.menu_editar_detalhe_produto -> {

                Intent(this, FormularioCadastroActivity::class.java).apply {

                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                }
            }
            R.id.menu_deletar_detalhe_produto -> {

                produto?.let {
                    produtoDao.remover(it)
                    finish()
                }

            }
        }


        return super.onOptionsItemSelected(item)
    }

    private fun preencherCampos(it: Produto) {
        binding.detalheProdutoTitulo.text = it.titulo
        binding.detalheProdutoBotaoDescricao.text = it.descricao
        binding.detalheProdutoImagemView.tentaCarregarImagemView(it.imagem)
        val valor = MoedaUtil.formatarMoedaBrasileira(it)
        binding.detalheProdutoBotaoValor.text = valor
    }
}