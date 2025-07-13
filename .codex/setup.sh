#!/usr/bin/env bash
set -euo pipefail

# ---------- base tools ----------
apt-get update -qq
DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
  openjdk-17-jdk git curl wget unzip build-essential \
  lib32stdc++6 lib32z1 python3.12 python3.12-venv python3-pip

# ---------- Android CLI tools ----------
ANDROID_HOME=/opt/android
mkdir -p "$ANDROID_HOME/cmdline-tools"
cd "$ANDROID_HOME"
wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O cmdtools.zip
unzip -q cmdtools.zip -d cmdline-tools && rm cmdtools.zip
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/bin:$ANDROID_HOME/platform-tools
yes | sdkmanager --licenses
sdkmanager --install "platform-tools" "platforms;android-34" "build-tools;34.0.0" "cmdline-tools;latest"

# ---------- Gradle & Kotlin ----------
curl -s https://get.sdkman.io | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle 8.8
sdk install kotlin 2.0.0

# ---------- Node 20 ----------
curl -fsSL https://deb.nodesource.com/setup_20.x | bash -
apt-get install -y nodejs

# ---------- Python data stack ----------
pip install --no-cache-dir numpy pandas matplotlib scipy

# ---------- clean-up ----------
apt-get clean && rm -rf /var/lib/apt/lists/*
echo "✅ Codex setup complete"
