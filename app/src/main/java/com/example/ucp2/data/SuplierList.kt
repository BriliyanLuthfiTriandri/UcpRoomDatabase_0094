package com.example.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.suplier.HomeSplrViewModel

object SuplierList {
    @Composable
    fun DataNama(
        splrHome: HomeSplrViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String>{
        val  getDataNama by splrHome.homeUiStateSuplier.collectAsState()
        val namaSplr = getDataNama.listSplr.map { it.nama }
        return namaSplr
    }
}