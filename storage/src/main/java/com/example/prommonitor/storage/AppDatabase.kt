package com.example.prommonitor.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prommonitor.storage.dao.EndpointDao
import com.example.prommonitor.storage.entity.Endpoint

@Database(entities = [Endpoint::class, Series::class, Sample::class, Metric::class, Insight::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun endpointDao(): EndpointDao
}
