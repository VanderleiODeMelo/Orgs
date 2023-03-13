package com.alura.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.alura.orgs.database.OrgsDataBase
import com.alura.orgs.databinding.ActivityListaProdutosBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.ui.activity.constantes.CHAVE_PRODUTO_ID
import com.alura.orgs.ui.adapter.recyclerview.ClickProdutoListener
import com.alura.orgs.ui.adapter.recyclerview.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity(), ClickProdutoListener {


    private val binding by lazy {

        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {

        ListaProdutosAdapter(context = this, emptyList(), listener = this)
    }
    private val produtoDao by lazy {

        OrgsDataBase.instance(this).produtoDao()
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Orgs"
        configurarFab()

        val layoutManager = LinearLayoutManager(this)
        binding.listaProdutosRecyclerview.layoutManager = layoutManager
        binding.listaProdutosRecyclerview.adapter = adapter


    }

    private fun configurarFab() {
        binding.listaProdutosAdicionarFab.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, FormularioCadastroActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onResume() {
        super.onResume()

        adapter.atualizarLista(produtoDao.buscarTodosProdutos())
    }

    override fun onItemClickListener(produto: Produto) {

         Intent(this, DetalheProdutoActivity::class.java)
            .apply {

                putExtra(CHAVE_PRODUTO_ID, produto.id)

                Log.i("TAG", "onItemClickListener: ${produto.titulo}")
                startActivity(this)
            }


    }
}