package com.example.ucp2.repository

import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySuplier {
    suspend fun insertSplr(suplier: Suplier)
    fun getAllSplr(): Flow<List<Suplier>>
    fun getSplr(id: String): Flow<Suplier>
}