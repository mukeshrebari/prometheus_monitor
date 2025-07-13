package com.example.prommonitor.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Endpoint(
    @PrimaryKey val id: Int? = null,
    val url: String,
    val token: String?
)
