package br.com.orgs.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.orgs.model.Produtos
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface ProdutoDao {
    @Query("SElECT * FROM Produtos ")
    fun buscaTodos(): List <Produtos>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun salva (vararg produtos: Produtos)

    @Delete
    fun remove (produtos: Produtos)

    @Query ("SELECT * FROM produtos WHERE id= :id")
    abstract fun buscaID(id:Long) : Produtos?

}