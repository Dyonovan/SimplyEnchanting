package com.dyonovan.simplyenchanting.api.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

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
public class EnchanterRecipeHandler implements IRecipeHandler<EnchanterRecipeWrapper> {

    @Override
    public Class<EnchanterRecipeWrapper> getRecipeClass() {
        return EnchanterRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid(EnchanterRecipeWrapper recipe) {
        return SEJeiPlugin.ENCHANTER_UUID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(EnchanterRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(EnchanterRecipeWrapper recipe) {
        return recipe.isValid();
    }
}
