package com.example.ucp2.repository

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBarang (
    private val barangDao: BarangDao
) : RepositoryBarang{

    override suspend fun insertBrg(barang: Barang){
        barangDao.insertBarang(barang)
    }
    override suspend fun deleteBrg(barang: Barang){
        barangDao.deleteBarang(barang)
    }
    override fun getAllBrg():Flow<List<Barang>> =
        barangDao.getAllBarang()

}