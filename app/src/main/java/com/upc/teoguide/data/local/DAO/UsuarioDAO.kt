package com.upc.teoguide.data.local.DAO

import androidx.room.*
import com.upc.teoguide.data.entities.Usuario

@Dao
interface UsuarioDAO {
    @Query("select * from usuarios")
    fun getAll(): MutableList<Usuario>

    @Query("select * from usuarios where id = :id")
    fun findById(id: Int): Usuario

    @Query("select id from usuarios where email = :email and password = :password")
    fun findUser(email: String, password : String): Int

    @Delete
    fun delete(usuario: Usuario)

    @Insert
    fun insert(vararg movie: Usuario)

    @Update
    fun update(vararg movie: Usuario)
}