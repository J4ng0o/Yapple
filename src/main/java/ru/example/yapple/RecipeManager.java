package ru.example.yapple;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class RecipeManager {

    // Имена предметов (используются для идентификации)
    public static final String YOUTH_APPLE_NAME = "§dМолодильное яблоко";
    public static final String AGING_APPLE_NAME = "§7Старческое яблоко";

    public static void registerRecipes(Plugin plugin) {
        // --- Молодильное яблоко: золотой одуванчик + яблоко ---
        ItemStack youthApple = createCustomApple(Material.APPLE, YOUTH_APPLE_NAME);
        NamespacedKey youthKey = new NamespacedKey(plugin, "youth_apple");
        ShapelessRecipe youthRecipe = new ShapelessRecipe(youthKey, youthApple);
        youthRecipe.addIngredient(Material.GOLDEN_DANDELION);
        youthRecipe.addIngredient(Material.APPLE);
        plugin.getServer().addRecipe(youthRecipe);

        // --- Старческое яблоко: костная мука + яблоко ---
        ItemStack agingApple = createCustomApple(Material.APPLE, AGING_APPLE_NAME);
        NamespacedKey agingKey = new NamespacedKey(plugin, "aging_apple");
        ShapelessRecipe agingRecipe = new ShapelessRecipe(agingKey, agingApple);
        agingRecipe.addIngredient(Material.BONE_MEAL);
        agingRecipe.addIngredient(Material.APPLE);
        plugin.getServer().addRecipe(agingRecipe);
    }

    private static ItemStack createCustomApple(Material base, String displayName) {
        ItemStack item = new ItemStack(base);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text(displayName));
            item.setItemMeta(meta);
        }
        return item;
    }
}
