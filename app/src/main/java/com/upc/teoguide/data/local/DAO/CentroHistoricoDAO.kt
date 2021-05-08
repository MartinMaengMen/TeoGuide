package com.upc.teoguide.data.local.DAO

import androidx.room.*
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.entities.Usuario

/*@Dao
interface CentroHistoricoDAO {
    @Query("select * from centros_historicos")
    fun getAll(): MutableList<CentroHistorico>

    @Query("select * from centros_historicos where id = :id")
    fun findById(id: Int): CentroHistorico

    @Delete
    fun delete(centroHistorico: CentroHistorico)

    @Insert
    fun insert(vararg centroHistorico: CentroHistorico)

    @Update
    fun update(vararg centroHistorico: CentroHistorico)
}*/