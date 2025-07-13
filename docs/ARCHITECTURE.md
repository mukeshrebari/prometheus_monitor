# Prometheus Mobile Monitor Architecture

This project is a multi-module Android application written in Kotlin.

- `core` provides networking clients, DTOs and a parser for Prometheus metrics.
- `storage` contains the SQLCipher enabled Room database.
- `worker` defines periodic WorkManager workers fetching metrics.
- `analytics` hosts statistical utilities including moving averages and anomaly detection.
- `app` is the Jetpack Compose UI.

Modules communicate via Kotlin interfaces and repository patterns.
