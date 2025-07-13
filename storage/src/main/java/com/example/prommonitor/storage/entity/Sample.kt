package com.example.prommonitor.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sample(
    @PrimaryKey val id: Long? = null,
    val seriesId: Long,
    val value: Double,
    val timestamp: Long
)
