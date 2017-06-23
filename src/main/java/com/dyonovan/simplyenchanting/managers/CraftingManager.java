package com.dyonovan.simplyenchanting.managers;

import com.dyonovan.simplyenchanting.Utils.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * This file was created for SimplyEnchanting1_12
 * <p>
 * SimplyEnchanting1_12 is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/23/2017
 */
public class CraftingManager {

    public static void preInit() {
        RecipeHelper.addShapedRecipe(new ItemStack(BlockManager.blockEnchantment),
                " B ",
                "DTD",
                "OOO", 'B', Items.BOOK, 'D', Items.DIAMOND, 'T', Blocks.ENCHANTING_TABLE, 'O', Blocks.OBSIDIAN);
    }
}
