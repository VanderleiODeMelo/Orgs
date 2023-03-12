package com.alura.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alura.orgs.R
import com.alura.orgs.dao.Dao
import com.alura.orgs.databinding.ActivityListaProdutosBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.ui.adapter.recyclerview.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity : AppCompatActivity() {


    private val binding by lazy {

        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val dao = Dao()
    private val adapter by lazy {

        ListaProdutosAdapter(this, dao.buscaTodosProdutos())
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



        dao.salvar(Produto(0, "Titulo 1", "Descrição 1", BigDecimal("1500")))



    }

    private fun configurarFab() {
        binding.listaProdutosAdicionarFab.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, FormularioCadastroActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onResume() {
        super.onResume()
        adapter.atualizarLista(dao.buscaTodosProdutos())
    }
}