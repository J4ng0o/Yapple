package ru.example.yapple;

import org.bukkit.plugin.java.JavaPlugin;

public class YApplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Регистрация рецептов
        RecipeManager.registerRecipes(this);
        // Регистрация слушателя событий
        getServer().getPluginManager().registerEvents(new ConsumeListener(), this);
        getLogger().info("YApplePlugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("YApplePlugin disabled.");
    }
}
