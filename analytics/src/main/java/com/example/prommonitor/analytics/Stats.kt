package com.example.prommonitor.analytics

import kotlin.math.pow
import kotlin.math.sqrt

object Stats {
    fun movingAverage(values: List<Double>, window: Int): List<Double> {
        if (window <= 0) return emptyList()
        return values.windowed(window) { it.average() }
    }

    fun percentile(values: List<Double>, p: Double): Double {
        if (values.isEmpty()) return Double.NaN
        val sorted = values.sorted()
        val k = (p * (sorted.size - 1)).toInt()
        return sorted[k]
    }

    fun zScores(values: List<Double>): List<Double> {
        val mean = values.average()
        val std = sqrt(values.sumOf { (it - mean).pow(2) } / values.size)
        return values.map { (it - mean) / std }
    }

    fun anomalies(values: List<Double>): List<Int> {
        return zScores(values).mapIndexedNotNull { idx, z -> if (kotlin.math.abs(z) > 3) idx else null }
    }

    fun forecastArima(values: List<Double>, steps: Int): List<Double> {
        // Stub
        return emptyList()
    }
}
