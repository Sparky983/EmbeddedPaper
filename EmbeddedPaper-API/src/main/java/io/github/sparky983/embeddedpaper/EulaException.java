package io.github.sparky983.embeddedpaper;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Thrown if you haven't agreed to the Minecraft EULA.
 *
 * @author Sparky983
 * @since 1.0.0
 */
public class EulaException extends RuntimeException {

    @Contract(pure = true)
    public EulaException() {

    }

    @Contract(pure = true)
    public EulaException(final @Nullable String message) {

        super(message);
    }
}
