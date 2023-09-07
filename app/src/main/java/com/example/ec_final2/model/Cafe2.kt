package com.example.ec_final2.model



data class Cafe2(

    val id: String = "",
    val tipo: String= "",
    val origen: String= "",
    val precio: Double= 0.0,
    val cantidad: Int= 0,
    val foto: String= "",
    var isFavorite: Boolean = false
)
