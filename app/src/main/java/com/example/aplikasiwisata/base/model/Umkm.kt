package com.example.aplikasiwisata.base.model

import com.google.gson.annotations.SerializedName

data class Umkm(
    val id: Int,
    @SerializedName("umkm_name")
    val umkmName: String?,
    val logo: String?,
    val website: String?,
    val phone: String?,
    val email: String?,
    val owner: String?,
    val address: String?,
    val kecamatan: String?,
    val kelurahan: String?,
    val kabupaten: String?,
    val rt: String?,
    val rw: String?,
    val postalCode: String?
)