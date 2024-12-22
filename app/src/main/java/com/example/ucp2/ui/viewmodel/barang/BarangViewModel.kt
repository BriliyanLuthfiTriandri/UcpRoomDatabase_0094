package com.example.ucp2.ui.viewmodel.barang



fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok.toIntOrNull()?.takeIf { it > 0 } ?: 0,
    namaSuplier = namaSuplier
)

data class BarangEvent(
    val id : String = "",
    val nama : String = "",
    val deskripsi : String = "",
    val harga : String = "",
    val stok : String = "",
    val namaSuplier : String = "",
)