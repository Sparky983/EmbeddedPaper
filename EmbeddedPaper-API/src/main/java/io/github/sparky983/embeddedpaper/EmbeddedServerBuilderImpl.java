package io.github.sparky983.embeddedpaper;

import static io.github.sparky983.embeddedpaper.EmbeddedServer.MAX_PORT;
import static io.github.sparky983.embeddedpaper.EmbeddedServer.MIN_PORT;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;
import java.util.ServiceLoader;

class EmbeddedServerBuilderImpl implements EmbeddedServer.Builder {

    private String ip = EmbeddedServer.DEFAULT_IP;
    private int port = EmbeddedServer.DEFAULT_PORT;
    private boolean eula = false;
    private ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

    @Override
    public EmbeddedServer.@NotNull Builder ip(final @NotNull String ip) {

        this.ip = Objects.requireNonNull(ip, "ip cannot be null");
        return this;
    }

    @SuppressWarnings("ConstantConditions") // Runtime check for port
    @Override
    public EmbeddedServer.@NotNull Builder port(
        final @Range(from = MIN_PORT, to = MAX_PORT) int port) {

        if (port < MIN_PORT || port > MAX_PORT) {
            throw new IllegalArgumentException("port is invalid");
        }

        this.port = port;
        return this;
    }

    @Override
    public EmbeddedServer.@NotNull Builder eula(final boolean eula) {

        this.eula = eula;
        return this;
    }

    @Override
    public EmbeddedServer.Builder classLoader(@NotNull ClassLoader classLoader) {

        this.classLoader = Objects.requireNonNull(classLoader, "classLoader cannot be null");
        return this;
    }

    @Override
    public @NotNull EmbeddedServer build() {

        if (!eula) {
            throw new EulaException("Please agree to the Minecraft EULA by using the .eula(true)");
        }

        final EmbeddedServerFactory factory = ServiceLoader.load(EmbeddedServerFactory.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No implementation found"));
        return factory.create(ip, port);
    }

    @Override
    public @NotNull EmbeddedServer start() {

        final EmbeddedServer server = build();
        server.start();
        return server;
    }
}
