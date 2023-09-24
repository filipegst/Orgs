package br.com.orgs.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.orgs.model.Produtos
import kotlinx.coroutines.flow.*

@Dao
interface ProdutoDao {
    @Query("SElECT * FROM Produtos ")
    fun buscaTodos(): Flow<List<Produtos>>


    @Query ("SELECT * FROM produtos WHERE usuarioid= :usuarioid")
    fun buscaTodosDoUsuario (usuarioid: String): Flow<List<Produtos>>

    @Query("SElECT * FROM Produtos ")
    suspend fun semOrdem(): List<Produtos>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva (vararg produtos: Produtos)

    @Delete
    suspend fun remove (produtos: Produtos)

    @Query ("SELECT * FROM produtos WHERE id= :id")
    fun buscaID(id: Long): Flow<Produtos?>

    @Query ("SELECT * FROM produtos ORDER BY NOME ASC")
    suspend fun buscaNomeAsc(): List<Produtos>

    @Query ("SELECT * FROM produtos ORDER BY NOME DESC")
    suspend fun buscaNomeDesc(): List<Produtos>

    @Query ("SELECT * FROM produtos ORDER BY Descricao ASC")
    suspend fun buscaDescricaoAsc(): List<Produtos>

    @Query ("SELECT * FROM produtos ORDER BY Descricao DESC")
    suspend fun buscaDescricaoDesc(): List<Produtos>

    @Query ("SELECT * FROM produtos ORDER BY Valor ASC")
    suspend fun buscaValorAsc(): List<Produtos>

    @Query ("SELECT * FROM produtos ORDER BY Valor DESC")
    suspend fun buscaValorDesc(): List<Produtos>
}