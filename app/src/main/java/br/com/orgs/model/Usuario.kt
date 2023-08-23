package br.com.orgs.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey
    val usuario: String,
    val senha : String
)