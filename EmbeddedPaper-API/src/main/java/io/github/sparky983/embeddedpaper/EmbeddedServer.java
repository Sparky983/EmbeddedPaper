package io.github.sparky983.embeddedpaper;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * The embedded server.
 *
 * @author Sparky983
 * @since 1.0.0
 */
public interface EmbeddedServer extends AutoCloseable {

    /**
     * The minimum port number (inclusive).
     *
     * @since 1.0.0
     */
    int MIN_PORT = 0x1;

    /**
     *The maximum port number (inclusive).
     *
     * @since 1.0.0
     */
    int MAX_PORT = 0xFFFF;

    /**
     * The default Minecraft port.
     *
     * @since 1.0.0
     */
    int DEFAULT_PORT = 25565;

    /**
     * The default ip.
     *
     * @since 1.0.0
     */
    @NotNull String DEFAULT_IP = "0.0.0.0";

    /**
     * Creates a new embedded server builder.
     *
     * @return The builder.
     * @since 1.0.0
     */
    @Contract(pure = true)
    static @NotNull EmbeddedServer.Builder builder() {

        return new EmbeddedServerBuilderImpl();
    }

    /**
     * Creates the embedded server with the default options.
     * <p>
     * Equivalent to {@code EmbeddedServer.builder().eula(eula).build()}.
     * <p>
     *
     * @param eula Whether you agree to the
     *             <a href="https://www.minecraft.net/en-us/eula">Minecraft EULA</a>
     * @return The embedded server.
     * @throws EulaException if the EULA has not been agreed to.
     * @since 1.0.0
     */
    static @NotNull EmbeddedServer create(final boolean eula) {

        return builder().eula(eula).build();
    }

    @Contract(pure = true)
    int getPort();

    /**
     * Starts the embedded server.
     *
     * @throws IllegalStateException if the server is already running.
     */
    @Blocking
    void start();

    /**
     * Stops the embedded server.
     *
     * @throws IllegalStateException if the server is not running.
     */
    @Blocking
    void stop();

    /**
     * Same as calling {@link #stop()}.
     *
     * @throws IllegalStateException if the server is not running.
     * @since 1.0.0
     */
    @Blocking
    @Override
    void close();

    /**
     * The embedded server builder.
     *
     * @since 1.0.0
     */
    interface Builder {

        /**
         * Specifies the ip to bind the server to.
         * <p>
         * Defaults to {@link #DEFAULT_IP}
         * </p>
         *
         * @param ip The ip.
         * @return The builder instance (for chaining).
         * @since 1.0.0
         */
        @Contract(value = "_ -> this", mutates = "this")
        @NotNull
        Builder ip(@NotNull String ip);

        /**
         * Specifies the port to bind the server to.
         * <p>
         * Defaults to {@link #DEFAULT_PORT}.
         * <p>
         *
         * @param port The port.
         * @return The builder instance (for chaining).
         * @throws IllegalArgumentException if {@code port} is not a valid port number.
         * @since 1.0.0
         */
        @Contract(value = "_ -> this", mutates = "this")
        @NotNull
        Builder port(@Range(from = MIN_PORT, to = MAX_PORT) int port);

        /**
         * Specifies that you agree to the eula.
         * <p>
         * This is obviously defaulted to false.
         * <p>
         *
         * @param eula Whether you agree to the
         *             <a href="https://www.minecraft.net/en-us/eula">Minecraft EULA</a>
         * @return The builder instance (for chaining).
         * @since 1.0.0
         */
        @Contract(value = "_ -> this", mutates = "this")
        @NotNull
        Builder eula(boolean eula);

        /**
         * Specifies the class loader to find the {@code plugin.yml} file and the plugin on.
         *
         * @param classLoader The class loader.
         * @return The builder instance (for chaining).
         * @since 1.0.0
         */
        Builder classLoader(@NotNull ClassLoader classLoader);

        /**
         * Builds the embedded server.
         *
         * @return The built embedded server.
         * @throws EulaException if the eula has not been agreed to - {@link #eula(boolean)}
         * @since 1.0.0
         */
        @NotNull
        EmbeddedServer build();

        /**
         * Builds and starts the embedded server.
         * <p>
         * This is most likely blocking.
         * <p>
         *
         * @return The started embedded server.
         * @throws EulaException if the eula has not been agreed to - {@link #eula(boolean)}
         * @since 1.0.0
         */
        @Blocking
        @NotNull
        EmbeddedServer start();
    }
}
