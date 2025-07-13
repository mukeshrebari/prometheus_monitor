package com.example.prommonitor.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Series(
    @PrimaryKey val id: Long? = null,
    val metricId: Long,
    val labels: String
)
