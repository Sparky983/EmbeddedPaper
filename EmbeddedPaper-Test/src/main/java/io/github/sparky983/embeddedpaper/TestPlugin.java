package io.github.sparky983.embeddedpaper;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

import net.kyori.adventure.text.Component;

import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

    public static boolean enabled = false;

    @Override
    public void onEnable() {

        enabled = true;
        getComponentLogger().info(Component.text("Enabled", GREEN));
    }

    @Override
    public void onDisable() {

        enabled = false;
    }
}
