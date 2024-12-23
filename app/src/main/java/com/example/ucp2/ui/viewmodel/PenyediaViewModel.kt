package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.WarungApp
import com.example.ucp2.ui.viewmodel.barang.BarangViewModel
import com.example.ucp2.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.ucp2.ui.viewmodel.suplier.HomeSplrViewModel
import com.example.ucp2.ui.viewmodel.suplier.SuplierViewModel

object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            HomeSplrViewModel(
                warungApp().containerApp.repositorySuplier
            )
        }
        initializer {
            HomeBrgViewModel(
                warungApp().containerApp.repositoryBarang
            )
        }
        initializer {
            BarangViewModel(
                warungApp().containerApp.repositoryBarang,
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                warungApp().containerApp.repositoryBarang,
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                warungApp().containerApp.repositoryBarang,
            )
        }

        initializer {
            SuplierViewModel(
                warungApp().containerApp.repositorySuplier,
            )
        }
    }
}


fun CreationExtras.warungApp(): WarungApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WarungApp)