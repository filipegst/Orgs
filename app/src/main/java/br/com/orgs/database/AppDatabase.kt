package br.com.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.orgs.converter.Converters
import br.com.orgs.model.Produtos
import br.com.orgs.model.Usuario


@Database(entities =
    [Produtos::class,
    Usuario::class],
    version = 2, exportSchema = true)


@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    abstract fun usuarioDao(): UsuarioDao
    companion object {
        @Volatile
        private var db: AppDatabase? = null
        fun instancia(context: Context): AppDatabase {
            return this.db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).fallbackToDestructiveMigration().build().also {
                this.db = it
            }
        }
    }
}
