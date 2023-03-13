package com.alura.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alura.orgs.conversores.Conversores
import com.alura.orgs.dao.ProdutoDao
import com.alura.orgs.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = true)
@TypeConverters(Conversores::class)
abstract class OrgsDataBase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao


    companion object {

        fun instance(context: Context):OrgsDataBase {

           return Room.databaseBuilder(context, OrgsDataBase::class.java, "orgs.db")
                .allowMainThreadQueries()
                .build()

        }


    }


}