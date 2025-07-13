# Prometheus Mobile Monitor – Codex Guide

This file gives Codex the rules and task scaffolds it should follow when working
in this repository.

## 👓 Project Overview
*Multi-module Android/Kotlin app that scrapes Prometheus metrics, stores them
locally (Room + SQLCipher), runs on-device analytics, and shows Compose charts.*

## 🗂 Feature-scaffold prompts
Below are reusable task blocks Codex can pick up.  
Codex agents often look for fenced code blocks labelled `codex-task`—keep each
under ~300 lines so we stay well below the 192 k-token limit.

```codex-task
### TASK: :core networking module
You are an expert Android/Kotlin architect.
Create module **:core** inside the Gradle multi-module project “Prometheus Mobile Monitor”.

Requirements
• Retrofit 2 + OkHttp 4 client pointing at Prometheus {user_input_url} [a microservice endpoint].
• Interface `PrometheusApi` with suspend functions `rawDataQuery()`.
• TLS **and** optional mutual-TLS: CertificatePinner for CA pinning, helper to load client cert + key into a custom SSLSocketFactory.
• Data DTOs → domain entities via Kotlin extension mappers.
• Unit tests with MockWebServer covering 200 + TLS failure.
Generate full Gradle files, Kotlin sources, and package `com.pmm.core.network`.
```

```codex-task
### TASK: :worker module
You are an expert Android/Kotlin architect.
Create module **:worker** (library) that depends on :core and :storage.

Responsibilities
• WorkManager periodic worker `ScrapeWorker` (default 15 min, configurable).
• Reads `Endpoint` table from Room, loops through each endpoint, calls `PrometheusApi.rawDataQuery`.
• Batches inserts into DB on IO dispatcher using transactions.
• Handles back-off and retry on HTTP 503 or network failure.
• Expose `scheduleScrapes()` helper called from :app at first launch.
Provide Kotlin source + unit test with `TestCoroutineDispatcher`.
```

```codex-task
### TASK: :storage module
You are an expert Android/Kotlin architect.
Create module **:storage** providing encrypted Room database.

Schema
• Entities: Metric(id,name,type), Series(id,metricId,labelsJSON), Sample(id,seriesId,ts,value),
            Insight(id,seriesId,kind,msg,ts), Endpoint(id,url,token,certAlias,intervalMin).
• Use **SQLCipher**; passphrase retrieved from DataStore Preferences.
• DAO returns Kotlin Flow for reactive charts.
• Insert helpers for histogram bucket grouping (_bucket/_sum/_count).
Include migration from v1→v2 (adds `Insight` table). 100 % test coverage with Robolectric.
Provide full Gradle files, Kotlin sources, and package `com.pmm.storage`.
```

```codex-task
### TASK: :analytics module
You are an expert Android/Kotlin architect.
Create module **:analytics** that depends on :storage.

Tasks
• Add Gradle deps `io.prometheus:prometheus4j`, `io.prometheus:promql_java_client`.
• Implement `PromqlEvaluator` wrapper to run queries against local Room data.
• Provide `Stats.kt` with movingAvg(), p95(), p99(), zScoreAnomaly(|z|>3) functions.
• Stub class `Forecaster` with ARIMA interface (no impl yet).
• JUnit tests validate z-score and percentile on synthetic data.
Provide full Gradle files, Kotlin sources, and package `com.pmm.analytics`.
```

```codex-task
### TASK: :ui/app module
You are an expert Android/Kotlin architect.
Create module **:app** (application) using Jetpack Compose Material 3.

Screens
1. On-boarding – add endpoints (URL, token, cert file).
2. Dashboard – LazyColumn of TenantCards (name, req/sec, avg latency).
3. Detail – Expanded card with LineChart (ComposeCharts preferred).
4. Live View – real-time chart with latest data, auto-refresh.
5. Insights – list of anomalies, grouped by series.
6. Heatmap – grid of series with color-coded values.
7. Labels – filterable list of all labels across series.
8. Labels - aggregated by label key, showing series count.
7. History – time range selector, LineChart with historical data.
9. Endpoint – details, edit/delete, manual scrape button.
10. Settings – toggle telemetry, set scrape interval, purge cache.

UI Requirements
• Dark/Light themes, pinch-zoom + pan on charts, legend toggles.
• Hilt DI, Navigation-Compose, DataStore for prefs.
• ForegroundService only in Detail “Live View”.

Produce `MainActivity`, composables, themes, preview files, AndroidManifest.
Provide full Gradle files, Kotlin sources, and package `com.pmm.ui`.
```
```codex-task
### TASK: :Security & Privacy Features
You are an expert Android/Kotlin architect.
Update :core and :app to meet Security / Privacy goals.

Tasks
• Credential storage via Android Keystore (MasterKey AES-GCM).
• Add `TelemetryManager` with opt-in flag (default false). No data sent unless enabled.
• Redact tokens in logs; provide `HttpLoggingInterceptor` that censors `Authorization`.
• Instrumented test verifying Keystore encryption path.
Generate code, README section “Security design”.
```
```codex-task
### TASK: :Testing & CI/CD
You are an expert Android/Kotlin architect.
Generate Dev-Ops assets for Prometheus Mobile Monitor.

Files
• `Makefile` – targets: dev (emulator), test (unit+instr), lint, ci-build.
• `.github/workflows/ci.yml`:
   – build dev-container,
   – run setup.sh,
   – gradlew lintDebug testDebugUnitTest connectedCheck.
• `docs/ARCHITECTURE.md` – module diagram + data flow.
• `docs/CONTRIBUTING.md` – branch naming, PR template, code-style.
Produce everything in correct paths.
```
```codex-task
### TASK: :Performance Guardrails
You are an expert Android/Kotlin architect.
Implement performance guardrails specific to Redmi Note 12 Pro Max.

Code additions
• Room open callback that enforces 10 GB WAL+main file quota and triggers LRU purge job.
• In-memory LRU cache with maxSize = 512 MB (Guava Cache).
• Live-View ForegroundService starts only while Detail screen in foreground; stops on pause.
Provide unit tests simulating DB > 10 GB triggering purge.
```
```codex-task
### TASK: :Documentation & Code Quality
You are an expert advocate for open source licensing.
Add Apache-2.0 license to project.

Files
• `LICENSE` root.
• Gradle publishing config in :app to include license metadata.
• Header template inserted at top of each Kotlin file via Spotless.
Generate License file + Spotless config.
```
```codex-task
### TASK: :core:parser module
You are an expert Android/Kotlin architect.
Inside module :core create package `parser`.

Implement
• `PromTextParser` for Prometheus text 0.0.4:
   – recognise `# HELP`, `# TYPE`, blank lines.
   – sample grammar: <metric>{label="v"} <value> [<ts_ms>].
   – regex validation of label keys `[a-zA-Z_][a-zA-Z0-9_]*`.
   – suffix/type inference (`*_total`, `*_bucket`…).
   – histogram assembly into Kotlin data class Histogram(val name, buckets, sum, count).
• `SampleRecord` data class as per spec.
• `parseOpenMetrics()` stub.
• JUnit tests with fixture strings (counter + histogram + malformed lines).
Deliver Kotlin sources + tests.
```
```codex-task
### TASK: :Container & Dev-Environment Stack
You are an expert DevOps engineer.
Create reproducible container environment for development.

Deliverables
1. `.codex/setup.sh`
   – Bash; install openjdk-17, Android cmdline-tools (platform-34, build-tools 34),
     Gradle 8.8, Kotlin 2.0 (SDKMAN), Node 20, Python3 numpy/pandas/matplotlib/scipy.
   – Accept licences automatically; clean apt cache.
2. `.devcontainer/Dockerfile`
   – FROM ghcr.io/openai/codex-universal:latest
   – Copy & RUN setup.sh.
3. `.devcontainer/devcontainer.json`
   – Post-create `gradle --version`; features java17, gradle8.8, node20; VS Code extensions list.
4. `scripts/run_local.sh` – launches same image locally.

Include comments in each file and README section “Developing in Codespaces”.
```




