package com.alura.orgs.ui.activity

import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alura.orgs.R
import com.alura.orgs.dao.Dao
import com.alura.orgs.databinding.ActivityDetalheProdutoBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.ui.activity.carregarImagem.tentaCarregarImagemView
import com.alura.orgs.util.MoedaUtil

class DetalheProdutoActivity : AppCompatActivity() {


    private lateinit var produto: Produto
    private val binding by lazy {

        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Detalhes"

        carregarDados()

    }

    private fun carregarDados() {

        val intent = intent
        if (intent.hasExtra("produto") && intent.hasExtra("posicao")) {

            if (VERSION.SDK_INT >= 33) {

                intent.getParcelableExtra("produto", Produto::class.java)?.let {
                    produto = it
                    carregandoInformacoes(it)
                }
            } else {
                intent.getParcelableExtra<Produto>("produto")?.let {

                    produto = it
                    carregandoInformacoes(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menus_opcoes_detalhe_produto, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(::produto.isInitialized){

            when (item.itemId) {
                R.id.menu_editar_detalhe_produto -> {
                    Log.i("TAG", "onOptionsItemSelected: Editar")

                }
                R.id.menu_deletar_detalhe_produto -> {
                    Log.i("TAG", "onOptionsItemSelected: Deletar: ${produto.titulo}")


                    finish()
                }
            }
        }


        return super.onOptionsItemSelected(item)
    }

    private fun carregandoInformacoes(it: Produto) {
        binding.detalheProdutoTitulo.text = it.titulo
        binding.detalheProdutoBotaoDescricao.text = it.descricao
        binding.detalheProdutoImagemView.tentaCarregarImagemView(it.imagem)
        val valor = MoedaUtil.formatarMoedaBrasileira(it)
        binding.detalheProdutoBotaoValor.text = valor
    }
}