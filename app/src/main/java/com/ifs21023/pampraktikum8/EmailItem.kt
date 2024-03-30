package com.ifs21023.pampraktikum8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class EmailItem (
    var nama: String,
    var icon: Int,
    var subjek: String,
    var teks: String,
) : Parcelable