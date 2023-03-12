package com.alura.orgs.ui.adapter.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.alura.orgs.R
import com.alura.orgs.databinding.ItemProdutoBinding
import com.alura.orgs.model.Produto
import com.alura.orgs.util.MoedaUtil.Companion.formatarMoedaBrasileira
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(private val context: Context, listaProdutos: List<Produto>) :
    Adapter<ListaProdutosAdapter.ProdutoViewHolder>() {

    private val lista = listaProdutos.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {

        val binding = ItemProdutoBinding.inflate(LayoutInflater.from(context), parent, false)

        return ProdutoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {

        val produto = lista[position]
        holder.vincular(produto)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun atualizarLista(lista: List<Produto>) {

        this.lista.clear()
        this.lista.addAll(lista)
        notifyDataSetChanged()

    }

    class ProdutoViewHolder(binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root) {

        private val titulo = binding.itemProdutoTitulo
        private val descricao = binding.itemProdutoDescricao
        private val valor = binding.itemProdutoValor
        private  val imagem =  binding.itemProdutoImagemView

        fun vincular(produto: Produto) {

            titulo.text = produto.titulo
            descricao.text = produto.descricao
            val valorFormatado = formatarMoedaBrasileira(produto)
            valor.text = valorFormatado

            if(produto.imagem != null){

                imagem.visibility = View.VISIBLE

            }else{

                imagem.visibility = View.GONE
            }
            imagem.load(produto.imagem){
                error(R.drawable.erro)
            }

        }



    }

}

