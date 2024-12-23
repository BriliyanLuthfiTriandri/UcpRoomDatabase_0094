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