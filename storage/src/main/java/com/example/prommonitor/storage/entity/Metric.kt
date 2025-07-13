package com.example.prommonitor.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Metric(
    @PrimaryKey val id: Long? = null,
    val name: String
)
