FROM ghcr.io/openai/codex-universal:latest
ENV CODEX_ENV_PYTHON_VERSION=3.12 \
    CODEX_ENV_NODE_VERSION=20 \
    DEBIAN_FRONTEND=noninteractive
COPY ./.codex/setup.sh /usr/local/bin/codex-setup.sh
RUN chmod +x /usr/local/bin/codex-setup.sh && /usr/local/bin/codex-setup.sh
