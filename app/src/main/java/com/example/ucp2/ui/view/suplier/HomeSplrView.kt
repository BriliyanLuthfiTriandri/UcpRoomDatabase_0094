package com.example.ucp2.ui.view.suplier


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.suplier.HomeSplrViewModel
import com.example.ucp2.ui.viewmodel.suplier.HomeUiStateSuplier
import kotlinx.coroutines.launch


@Composable
fun HomeSplrView(
    viewModel: HomeSplrViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddSplr: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    onBack : () -> Unit = { },
    modifier: Modifier
) {
    Scaffold (
        modifier = Modifier
            .padding(top = 35.dp),
        topBar = {
            TopAppBar(
                judul = "List Suplier",
                showBackButton = true,
                onBack = onBack
            )
        },

    ) { innerPadding ->
        val homeUiState by viewModel.homeUiStateSuplier.collectAsState()
        BodyHomeSplrView(
            homeUiStateSuplier = homeUiState,
            onClick = {
                onDetailClick(it) },
                modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeSplrView (
    homeUiStateSuplier: HomeUiStateSuplier,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiStateSuplier.isLoading -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiStateSuplier.isError -> {
            LaunchedEffect(homeUiStateSuplier.errorMessage) {
                homeUiStateSuplier.errorMessage?.let{ message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiStateSuplier.listSplr.isEmpty() -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text (
                    text = "Tidak ada data Suplier. ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListSuplier(
                listSplr =  homeUiStateSuplier.listSplr,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSuplier(
    listSplr: List<Suplier>,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(
            items = listSplr,
            itemContent = { splr ->
                CardSplr(
                    splr = splr,
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSplr(
    splr: Suplier,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconid),
                    contentDescription = "Icon",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = splr.id,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = splr.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = splr.alamat,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = splr.kontak,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
