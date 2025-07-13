# Prometheus Mobile Monitor

An Android application for viewing Prometheus metrics on-device.

## Modules
- **app** – Compose UI.
- **core** – networking and parser.
- **worker** – periodic WorkManager tasks.
- **storage** – SQLCipher Room database.
- **analytics** – statistics utilities.

Run `./gradlew assembleDebug` to build.

## Manual Steps
- Generate an Android keystore and set signing environment variables.
- Store the SQLCipher passphrase in local secrets.
- Provide Prometheus endpoint URLs and tokens for testing.
- (Optional) specify client certificate and CA bundle paths for mutual TLS.
