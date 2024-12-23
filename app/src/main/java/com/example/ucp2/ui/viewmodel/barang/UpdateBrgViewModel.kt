package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBarang
import com.example.ucp2.ui.navigation.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch



fun Barang.toUIStateBrg(): BrgUIState = BrgUIState (
    barangEvent = this.toDetailUiEvent()
)