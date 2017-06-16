package com.dyonovan.simplyenchanting.managers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * This file was created for SimplyEnchanting
 * <p>
 * SimplyEnchanting is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/16/2017
 */
public class CraftingManager {

    public static void preInit() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.blockEnchantment),
                " B ",
                "DTD",
                "OOO", 'B', Items.BOOK, 'D', Items.DIAMOND, 'T', Blocks.ENCHANTING_TABLE, 'O', Blocks.OBSIDIAN));
    }
}
