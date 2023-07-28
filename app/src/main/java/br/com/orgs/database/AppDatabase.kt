package br.com.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.orgs.converter.Converters
import br.com.orgs.model.Produtos

@Database(entities = [Produtos::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun instancia (context: Context) :AppDatabase  {
       return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "orgs.db"
        ).allowMainThreadQueries().build()
        }
    }

}