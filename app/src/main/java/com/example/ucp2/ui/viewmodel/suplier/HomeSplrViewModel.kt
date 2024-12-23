package com.example.ucp2.ui.viewmodel.suplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySuplier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeSplrViewModel(
    private val repositorySuplier: RepositorySuplier
) : ViewModel() {
    val homeUiStateSuplier: StateFlow<HomeUiStateSuplier> = repositorySuplier.getAllSplr()
        .filterNotNull()
        .map {
            HomeUiStateSuplier(
                listSplr = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiStateSuplier(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateSuplier(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateSuplier(
                isLoading = true,
            )
        )
}


data class HomeUiStateSuplier(
    val listSplr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
)