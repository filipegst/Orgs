package br.com.orgs.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val Migration_2_3 = object : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `Usuario` (`id` TEXT NOT NULL, `senha` TEXT NOT NULL, PRIMARY KEY(`id`))")
    }
}