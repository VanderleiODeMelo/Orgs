package com.alura.orgs.dao

import androidx.room.*
import com.alura.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodosProdutos(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(produto: Produto)

    @Delete
    fun remover(produto: Produto)

    @Query("SELECT * FROM PRODUTO WHERE id = :id")
    fun buscaPorId(id: Long):Produto? //podemos receber nulo pois se eu mandar id 1000 que não tem

}