package com.alura.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

        configuraRecyclerview()

        val refreshLayout = binding.listaProdutosAdicionarSwipeRefreshLayout
        binding.listaProdutosAdicionarSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {

            val listaProdutos = produtoDao.buscarTodosProdutos()

            refreshLayout.isRefreshing = false
            adapter.atualizarLista(listaProdutos)
            Toast.makeText(this,
                "Lista atualizada com sucesso !",
                Toast.LENGTH_SHORT).show()
        })

    }

    private fun configuraRecyclerview() {

        configuraLayoutManager()
        configuraAdapter()
    }

    private fun configuraAdapter() {
        binding.listaProdutosRecyclerview.adapter = adapter
    }

    private fun configuraLayoutManager() {
        val layoutManager = LinearLayoutManager(this)
        binding.listaProdutosRecyclerview.layoutManager = layoutManager
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
                startActivity(this)
            }
    }
}