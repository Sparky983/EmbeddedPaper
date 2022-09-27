package io.github.sparky983.embeddedpaper;

import static io.github.sparky983.embeddedpaper.EmbeddedServer.MAX_PORT;
import static io.github.sparky983.embeddedpaper.EmbeddedServer.MIN_PORT;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * A factory for embedded server implementations to create server instances from.
 *
 * @author Sparky983
 * @since 1.0.0
 */
@ApiStatus.OverrideOnly
public interface EmbeddedServerFactory {

    /**
     * Creates a new embedded server.
     *
     * @param ip The ip.
     * @param port The port.
     * @return The created server.
     * @throws NullPointerException if ip is {@code null}.
     * @throws IllegalArgumentException if {@code port} is not a valid port number.
     * @since 1.0.0
     */
    @NotNull EmbeddedServer create(@NotNull String ip, @Range(from = MIN_PORT, to = MAX_PORT) int port);
}
