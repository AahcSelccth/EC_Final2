package com.example.ec_final2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "cafe")
data class Cafe(

    @PrimaryKey
    val id: Int,
    val tipo: String,
    val origen: String,
    val precio: Double,
    val cantidad: Int,
    val foto: String,
    var isFavorite: Boolean = false
) : Parcelable {}
