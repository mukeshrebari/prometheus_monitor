#!/usr/bin/env bash
docker run --rm -it \
  -e ANDROID_HOME=/opt/android \
  -v "$PWD":/workspace -w /workspace \
  ghcr.io/openai/codex-universal:latest /bin/bash -c "/usr/local/bin/codex-setup.sh && bash"
