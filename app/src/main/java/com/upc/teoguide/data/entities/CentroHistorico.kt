package com.upc.teoguide.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "centros_historicos")
data class CentroHistorico (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "description")
    val description : String?
        )