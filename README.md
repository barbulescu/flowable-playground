# flowable-playground

## Running Tests with Testcontainers (Podman)

Testcontainers requires a running container runtime. If you're using Podman instead of Docker, follow these steps.

### 1. Start the Podman machine

```bash
podman machine start
```

### 2. Enable the Podman socket

```bash
podman machine ssh 'sudo systemctl enable --now podman.socket'
```

### 3. Set the Docker host environment variable

Find the socket path and export it:

```bash
export DOCKER_HOST="unix://$(podman machine inspect --format '{{.ConnectionInfo.PodmanSocket.Path}}')"
```

To make this permanent, add it to your `~/.zshrc`:

```bash
echo 'export DOCKER_HOST="unix://$(podman machine inspect --format "{{.ConnectionInfo.PodmanSocket.Path}}")"' >> ~/.zshrc
```

### 4. Disable Ryuk

Testcontainers' Ryuk resource reaper can cause issues with Podman. Disable it by setting:

```bash
export TESTCONTAINERS_RYUK_DISABLED=true
```

Or add to `~/.testcontainers.properties`:

```
ryuk.disabled=true
```

### 5. Run the build

```bash
./gradlew test
```