package com.example.ucp2.ui.viewmodel.barang




data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null,
){
    fun isValid(): Boolean{
        return id == null && nama == null && deskripsi == null &&
                harga == null  && stok == null && namaSuplier == null
    }
}

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