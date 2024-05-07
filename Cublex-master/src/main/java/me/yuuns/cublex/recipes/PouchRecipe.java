package me.yuuns.cublex.recipes;

import me.yuuns.cublex.Cublex;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class PouchRecipe {
    public PouchRecipe() {
        NamespacedKey key = new NamespacedKey(Cublex.getInstance(), "pouch");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BUNDLE));
        recipe.shape(
                "LSL",
                "LEL",
                "LLL"
        );
        recipe.setIngredient('L', Material.RABBIT_HIDE);
        recipe.setIngredient('S', Material.LEAD);
        recipe.setIngredient('E', Material.ENDER_CHEST);
        Bukkit.addRecipe(recipe);
    }
}
