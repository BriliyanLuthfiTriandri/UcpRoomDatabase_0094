package com.example.ucp2.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySuplier
import com.example.ucp2.ui.viewmodel.barang.BarangEvent
import kotlinx.coroutines.launch

class SuplierViewModel(private val repositorySuplier: RepositorySuplier): ViewModel() {

    var uiStateSplr by mutableStateOf(SplrUIState())


    fun updateState(suplierEvent: SuplierEvent){
        uiStateSplr = uiStateSplr.copy(
            suplierEvent = suplierEvent,
        )
    }

    // Validasi data import pengguna
    private fun validateFields(): Boolean {
        val event = uiStateSplr.suplierEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "Id tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
        )
        uiStateSplr = uiStateSplr.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiStateSplr.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySuplier.insertSplr(currentEvent.toSuplierEntity())
                    uiStateSplr = uiStateSplr.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        suplierEvent = SuplierEvent(), //Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                } catch (e: Exception) {
                    uiStateSplr = uiStateSplr.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiStateSplr = uiStateSplr.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }
    //Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiStateSplr = uiStateSplr.copy(snackBarMessage = null)
    }
}

data class SplrUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null,
){
    fun isValid(): Boolean {
        return id == null && nama == null && kontak == null &&
                alamat == null
    }
}

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat,
)

data class SuplierEvent(
    val id : String = "",
    val nama : String = "",
    val kontak : String = "",
    val alamat : String = ""
)