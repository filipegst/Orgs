package br.com.orgs.database

import androidx.room.Dao
import androidx.room.Insert
import br.com.orgs.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    suspend fun salva (usuario: Usuario)

}