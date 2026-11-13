package ru.example.yapple;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConsumeListener implements Listener {

    private static final double MIN_SCALE = 0.75;
    private static final double MAX_SCALE = 1.15;
    private static final double STEP = 0.05; // шаг изменения роста

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String name = meta.getDisplayName();
        Player player = event.getPlayer();
        AttributeInstance scaleAttr = player.getAttribute(Attribute.SCALE);

        if (scaleAttr == null) {
            player.sendMessage("§cОшибка: атрибут SCALE недоступен.");
            return;
        }

        double current = scaleAttr.getBaseValue();
        double newValue;

        if (name.equals(RecipeManager.YOUTH_APPLE_NAME)) {
            // Уменьшение роста на 0.05, но не ниже 0.75
            newValue = Math.max(MIN_SCALE, current - STEP);
            if (newValue >= current) {
                player.sendMessage("§eВы уже достигли минимального роста (0.75).");
                return;
            }
        } else if (name.equals(RecipeManager.AGING_APPLE_NAME)) {
            // Увеличение роста на 0.05, но не выше 1.15
            newValue = Math.min(MAX_SCALE, current + STEP);
            if (newValue <= current) {
                player.sendMessage("§eВы уже достигли максимального роста (1.15).");
                return;
            }
        } else {
            return; // Не наш предмет
        }

        // Округляем до 2 знаков, чтобы избежать накопления ошибки double
        newValue = Math.round(newValue * 100.0) / 100.0;

        scaleAttr.setBaseValue(newValue);
        player.sendMessage("§aВаш рост теперь: §f" + String.format("%.2f", newValue));
    }
}
