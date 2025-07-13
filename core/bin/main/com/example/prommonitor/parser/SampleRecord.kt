package com.example.prommonitor.parser

data class SampleRecord(
    val metric: String,
    val labels: Map<String, String>,
    val value: Double,
    val ts: Long
)
