package com.example.prommonitor.parser

import org.junit.Assert.assertEquals
import org.junit.Test

class PrometheusTextParserTest {
    @Test
    fun parseValid() {
        val text = """
# HELP http_requests_total The total number of HTTP requests.
# TYPE http_requests_total counter
http_requests_total{method="post",code="200"} 1027 1395066363000
"""
        val parser = PrometheusTextParser()
        val result = parser.parse(text.reader())
        assertEquals(1, result.size)
        assertEquals("http_requests_total", result[0].metric)
        assertEquals("post", result[0].labels["method"])
        assertEquals(1027.0, result[0].value, 0.001)
    }

    @Test
    fun parseInvalid() {
        val text = "bad line"
        val parser = PrometheusTextParser()
        val result = parser.parse(text.reader())
        assertEquals(0, result.size)
    }
}
