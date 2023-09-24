package br.com.orgs.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val Migration_3_4 = object : Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Produto ADD COLUMN 'usuarioid' TEXT ")
    }
}