package com.example.ucp2.ui.viewmodel.barang


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBarang
import kotlinx.coroutines.launch

class BarangViewModel(private val repositoryBarang: RepositoryBarang
): ViewModel() {

    var uiStateBrg by mutableStateOf(BrgUIState())

    // Memperbarui state berdasarkan input pengguna
    fun updateState(barangEvent: BarangEvent) {
        uiStateBrg = uiStateBrg.copy(
            barangEvent = barangEvent,
        )
    }

    // Validasi data import pengguna
    private fun validateFields(): Boolean {
        val event = uiStateBrg.barangEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "Id tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Tidak Boleh Kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )
        uiStateBrg = uiStateBrg.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
}


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