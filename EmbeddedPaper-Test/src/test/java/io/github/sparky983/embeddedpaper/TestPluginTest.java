package io.github.sparky983.embeddedpaper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestPluginTest {

    static EmbeddedServer server;

    @BeforeAll
    static void setUp() {

        server = EmbeddedServer.builder()
            .eula(true)
            .start();
    }

    @AfterAll
    static void tearDown() {

        server.close();
    }

    @Test
    void plugin_Enables_WhenServerStarts() {

        assertTrue(TestPlugin.enabled);
    }
}
