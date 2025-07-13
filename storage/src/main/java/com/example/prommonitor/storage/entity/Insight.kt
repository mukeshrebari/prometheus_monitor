package com.example.prommonitor.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Insight(
    @PrimaryKey val id: Long? = null,
    val seriesId: Long,
    val type: String,
    val message: String,
    val timestamp: Long
)
