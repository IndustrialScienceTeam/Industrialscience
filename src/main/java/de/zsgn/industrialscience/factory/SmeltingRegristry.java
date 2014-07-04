package de.zsgn.industrialscience.factory;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SmeltingRegristry {

    public static float getSmeltTemp(ItemStack itemStack) {
        return 200;
    }

    public static ItemStack getSmeltingResult(ItemStack itemStack) {
        return FurnaceRecipes.smelting().getSmeltingResult(itemStack);
    }

}
