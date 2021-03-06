package com.upc.teoguide.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "usuarios")
data class Usuario (
    @PrimaryKey
    var id : Int,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "password")
    val password: String?,
    @ColumnInfo(name = "name")
    val name: String?
) : Serializable {
    constructor() : this(
    0,
    "",
    "",
    ""
    )
}