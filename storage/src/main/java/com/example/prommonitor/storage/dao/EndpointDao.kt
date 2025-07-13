package com.example.prommonitor.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.prommonitor.storage.entity.Endpoint
import kotlinx.coroutines.flow.Flow

@Dao
interface EndpointDao {
    @Query("SELECT * FROM Endpoint")
    fun getAll(): Flow<List<Endpoint>>

    @Query("SELECT * FROM Endpoint")
    suspend fun getAllOnce(): List<Endpoint>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(endpoints: List<Endpoint>)
}
