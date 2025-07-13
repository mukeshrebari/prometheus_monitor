# Re-use OpenAI’s dev image so Codespaces ≈ Codex
FROM ghcr.io/openai/codex-universal:latest                                            # base image :contentReference[oaicite:5]{index=5}

# ENV vars that codex-universal understands
ENV CODEX_ENV_PYTHON_VERSION=3.12 \
    CODEX_ENV_NODE_VERSION=20 \
    DEBIAN_FRONTEND=noninteractive

# Copy Codex bootstrap (it’s safe for Codespaces too)
COPY ./.codex/setup.sh /usr/local/bin/codex-setup.sh
RUN chmod +x /usr/local/bin/codex-setup.sh && /usr/local/bin/codex-setup.sh
