package com.dyonovan.simplyenchanting.api.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;

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
public class EnchanterRecipeWrapper extends BlankRecipeWrapper {

    private ItemStack item;
    private ItemStack lapis;
    private ItemStack enchBook;
    private int xp;

    public EnchanterRecipeWrapper(ItemStack itemIn, ItemStack lapisIn, int xpIn, ItemStack enchBookIn) {
        this.item = itemIn;
        this.lapis = lapisIn;
        this.xp = xpIn;
        this.enchBook = enchBookIn;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ArrayList<ItemStack> inputs = new ArrayList<>();
        inputs.add(item);
        inputs.add(lapis);
        inputs.add(new ItemStack(Items.BOOK));
        ingredients.setInputs(ItemStack.class, inputs);

        ingredients.setOutput(ItemStack.class, enchBook);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        String exp = I18n.format("simplyenchanting:expRequired") + ": " + xp;
        minecraft.fontRendererObj.drawString(exp, 175 / 2 - (minecraft.fontRendererObj.getStringWidth(exp) / 2), 32, Color.gray.getRGB());
    }

    boolean isValid() {
        return true;
    }
}
