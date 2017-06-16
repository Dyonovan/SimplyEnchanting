package com.dyonovan.simplyenchanting.api.jei;

import com.dyonovan.simplyenchanting.client.guis.GuiEnchantment;
import com.dyonovan.simplyenchanting.managers.BlockManager;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

/**
 * This file was created for SimplyEnchanting
 * <p>
 * SimplyEnchanting is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/15/2017
 */
@JEIPlugin
public class SEJeiPlugin implements IModPlugin {

    public static IJeiHelpers jeiHelpers;

    public static final String ENCHANTER_UUID    = "simplyenchanting.enchanter";

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {

    }

    @Override
    public void register(IModRegistry registry) {
        jeiHelpers = registry.getJeiHelpers();

        registry.addRecipeCategories(new EnchanterRecipeCategories());
        registry.addRecipeHandlers(new EnchanterRecipeHandler());

        registry.addRecipes(EnchanterRecipeCategories.buildRecipeList());

        registry.addRecipeCatalyst(new ItemStack(BlockManager.blockEnchantment), ENCHANTER_UUID);
        registry.addRecipeClickArea(GuiEnchantment.class, 155, 5, 16, 16, ENCHANTER_UUID);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

    }
}
