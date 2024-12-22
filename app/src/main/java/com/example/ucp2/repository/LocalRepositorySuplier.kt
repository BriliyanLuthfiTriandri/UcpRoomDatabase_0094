package com.example.ucp2.repository

import com.example.ucp2.data.dao.SuplierDao
import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySuplier (
    private val suplierDao: SuplierDao
) : RepositorySuplier{

    override suspend fun insertSplr(suplier: Suplier){
        suplierDao.insertSuplier(suplier)
    }

}