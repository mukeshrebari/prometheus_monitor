package com.example.prommonitor.parser

import java.io.Reader
import java.util.regex.Pattern

class PrometheusTextParser {
    private val typeMap = mutableMapOf<String, String>()

    fun parse(reader: Reader): List<SampleRecord> {
        val result = mutableListOf<SampleRecord>()
        reader.forEachLine { line ->
            val trimmed = line.trim()
            if (trimmed.isEmpty()) return@forEachLine
            if (trimmed.startsWith("#")) {
                handleDirective(trimmed)
            } else {
                parseSample(trimmed)?.let { result += it }
            }
        }
        return result
    }

    private fun handleDirective(line: String) {
        val parts = line.split(" ", limit = 3)
        if (parts.size >= 3 && parts[1] == "TYPE") {
            val metricAndType = parts[2].split(" ", limit = 2)
            val metric = metricAndType.getOrElse(0) { "" }
            val type = metricAndType.getOrElse(1) { "" }
            typeMap[metric] = type
        }
    }

    private fun parseSample(line: String): SampleRecord? {
        val regex = Regex("^([a-zA-Z_:][a-zA-Z0-9_:]*)\\s*(\{[^}]*\})?\\s+([+-]?[0-9.]+)(?:\\s+(\\d+))?")
        val match = regex.matchEntire(line) ?: return null
        val metric = match.groupValues[1]
        val labelPart = match.groupValues[2]
        val value = match.groupValues[3].toDouble()
        val ts = match.groupValues.getOrNull(4)?.toLongOrNull() ?: 0L
        val labels = parseLabels(labelPart)
        return SampleRecord(metric, labels, value, ts)
    }

    private fun parseLabels(raw: String): Map<String, String> {
        if (raw.isBlank()) return emptyMap()
        val content = raw.removePrefix("{").removeSuffix("}")
        return if (content.isBlank()) emptyMap() else content.split(",").associate {
            val kv = it.split("=")
            val key = kv[0].trim()
            val value = kv[1].trim().removeSurrounding("\"")
            key to value
        }
    }
}

fun parseOpenMetrics(reader: Reader) = emptyList<SampleRecord>()
