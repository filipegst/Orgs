package br.com.orgs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.orgs.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert
    suspend fun salva (usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE id = :usuarioid AND senha = :senha")
    suspend fun autentica(
        usuarioid: String,
        senha: String
    ): Usuario?


    @Query("SELECT * FROM Usuario WHERE id =:usuarioid")
    fun buscaId (usuarioid: String) : Flow<Usuario>

}