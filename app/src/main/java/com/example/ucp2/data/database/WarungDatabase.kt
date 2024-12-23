package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SuplierDao
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class WarungDatabase : RoomDatabase(){

    abstract fun BarangDao() : BarangDao
    abstract fun SuplierDao() : SuplierDao

    companion object {
        @Volatile
        private var Instances: WarungDatabase? =  null

        fun getDatabase(context: Context) : WarungDatabase {
            return (Instances ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    WarungDatabase::class.java,
                    "WarungDatabase"
                )
                    .build().also { Instances = it }
            })
        }
    }
}
