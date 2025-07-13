package com.example.prommonitor.model

data class MetricDto(
    val metric: Map<String, String>,
    val values: List<List<String>>
)

fun MetricDto.toDomain(): Metric = Metric(metric, values)

data class Metric(val labels: Map<String, String>, val values: List<List<String>>)
