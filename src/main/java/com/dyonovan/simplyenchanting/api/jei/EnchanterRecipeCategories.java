package com.dyonovan.simplyenchanting.api.jei;

import com.dyonovan.simplyenchanting.lib.EnchantmentRecipe;
import com.dyonovan.simplyenchanting.lib.Reference;
import com.dyonovan.simplyenchanting.managers.ConfigManager;
import com.dyonovan.simplyenchanting.managers.RecipeManager;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class EnchanterRecipeCategories implements IRecipeCategory<EnchanterRecipeWrapper> {

    private ResourceLocation backgroundResource = new ResourceLocation(Reference.MOD_ID, "textures/gui/guienchanting.png");

    @Override
    public String getUid() {
        return SEJeiPlugin.ENCHANTER_UUID;
    }

    @Override
    public String getTitle() {
        return I18n.format("simplyenchanting:guiEnchantment");
    }

    @Override
    public String getModName() {
        return Reference.MOD_NAME;
    }

    @Override
    public IDrawable getBackground() {
        return SEJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(backgroundResource, 5, 5, 165, 75);
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, EnchanterRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 25, 7);
        itemStackGroup.init(1, true, 74, 7);
        itemStackGroup.init(2, true, 123, 7);
        itemStackGroup.init(3, false, 74, 43);

        recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
        recipeLayout.getItemStacks().set(1, ingredients.getInputs(ItemStack.class).get(1));
        recipeLayout.getItemStacks().set(2, ingredients.getInputs(ItemStack.class).get(2));
        recipeLayout.getItemStacks().set(3, ingredients.getOutputs(ItemStack.class).get(0));
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    public static List<EnchanterRecipeWrapper> buildRecipeList() {
        ArrayList<EnchanterRecipeWrapper> recipes = new ArrayList<>();

        for (EnchantmentRecipe recipe : RecipeManager.enchantmentRecipes) {
            ItemStack enchBook = new ItemStack(Items.ENCHANTED_BOOK);
            enchBook.addEnchantment(Enchantment.getEnchantmentByLocation(recipe.eName), 1);
            recipes.add(new EnchanterRecipeWrapper(
                    RecipeManager.getItemStack(recipe.eItem),
                    new ItemStack(Items.DYE, ConfigManager.lapisModifier, 4),
                    ConfigManager.xpModifier,
                    enchBook
            ));
        }

        return recipes;
    }
}
