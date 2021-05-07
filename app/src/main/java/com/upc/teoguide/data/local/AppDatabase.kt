package com.upc.teoguide.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.DAO.CentroHistoricoDAO
import com.upc.teoguide.data.local.DAO.UsuarioDAO

@Database(entities = [Usuario::class, CentroHistorico::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDAO(): UsuarioDAO
    abstract fun centroHistoricoDAO(): CentroHistoricoDAO

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, AppDatabase::class.java, "teoguide.db").build()
            }
            return INSTANCE as AppDatabase
        }
    }
}