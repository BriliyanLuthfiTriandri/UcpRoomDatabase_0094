package com.example.ucp2.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.SuplierList
import com.example.ucp2.ui.customwidget.DynamicSelectTextField
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.barang.BarangEvent
import com.example.ucp2.ui.viewmodel.barang.BarangViewModel
import com.example.ucp2.ui.viewmodel.barang.BrgUIState
import com.example.ucp2.ui.viewmodel.barang.FormErrorState
import kotlinx.coroutines.launch




@Composable
fun InsertBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory) // Inisialisasi viewModel
){
    val uiState = viewModel.uiStateBrg // Ambil UI State dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackBarMessage
    LaunchedEffect (uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message) //Tampilkan SnackHolder
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Barang"
            )
            // Isi Body
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent) // Update State di ViewModel
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FormBarang(
    modifier: Modifier = Modifier,
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit =  {},
    errorState: FormErrorState = FormErrorState()
){
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.id,
            onValueChange = {
                onValueChange(barangEvent.copy(id = it))
            },
            label = { Text("ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorState.id != null,
            placeholder = { Text("Masukan ID") },
        )
        Text(
            text = errorState.id ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukan Nama Anda") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukan Deskripsi Barang") },
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorState.harga != null,
            placeholder = { Text("Masukan Harga Barang") },
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("Stok") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukan Stok") },
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )

        DynamicSelectTextField(
            label = "Nama Suplier",
            modifier = Modifier,
            selectedValue = barangEvent.namaSuplier,
            onValueChangedEvent = { selectedSplr ->
                onValueChange(barangEvent.copy(namaSuplier = selectedSplr))
            },
            options = SuplierList.DataNama(),
            isError = errorState.namaSuplier != null
        )
        Text(
            text = errorState.namaSuplier ?: "",
            color = Color.Red
        )

    }
}