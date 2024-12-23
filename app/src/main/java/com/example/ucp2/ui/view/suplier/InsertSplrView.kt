package com.example.ucp2.ui.view.suplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.suplier.FormErrorState
import com.example.ucp2.ui.viewmodel.suplier.SplrUIState
import com.example.ucp2.ui.viewmodel.suplier.SuplierEvent
import com.example.ucp2.ui.viewmodel.suplier.SuplierViewModel
import kotlinx.coroutines.launch


@Composable
fun InsertBodySplr(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit,
    uiState: SplrUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormSuplier(
            suplierEvent = uiState.suplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF91A4) // Mengatur warna latar belakang tombol
            )
        ) {
            Text("Simpan")
        }

    }
}


@Composable
fun InsertSplrView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplierViewModel = viewModel(factory = PenyediaViewModel.Factory ) // Inisialisasi viewModel
){
    val uiState = viewModel.uiStateSplr // Ambil UI State dari ViewModel
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
        modifier = modifier
            .padding(top = 10.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ){ padding ->
        TopAppBar(
            onBack = onBack,
            showBackButton = true,
            judul = "Tambah Suplier"
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
                .padding(top = 80.dp)
        ){
            // Isi Body
            InsertBodySplr(
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
fun FormSuplier(
    suplierEvent: SuplierEvent = SuplierEvent(),
    onValueChange: (SuplierEvent) -> Unit =  {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier.fillMaxWidth()
    ){

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.id ,
            onValueChange = {
                onValueChange(suplierEvent.copy(id = it))
            },
            label = { Text("ID") },
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = " ") },
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
            value = suplierEvent.nama,
            onValueChange = {
                onValueChange(suplierEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = " ")},
            isError = errorState.nama != null,
            placeholder = { Text("Masukan Nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = {
                onValueChange(suplierEvent.copy(kontak = it))
            },
            label = { Text("Kontak") },
            leadingIcon = { Icon(imageVector = Icons.Default.Call, contentDescription = " ")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = errorState.kontak != null,
            placeholder = { Text("Masukan Nomor Anda") },
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = {
                onValueChange(suplierEvent.copy(alamat = it))
            },
            label = { Text("Alamat") },
            leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = " ")},
            isError = errorState.alamat != null,
            placeholder = { Text("Masukan Alamat Anda") },
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
    }
}