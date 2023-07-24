package br.com.orgs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.orgs.model.Produtos

@Dao
interface ProdutoDao {
    @Query("SElECT * FROM Produtos ")
    fun buscaTodos(): List <Produtos>

    @Insert
    fun salva (vararg produtos: Produtos)
}