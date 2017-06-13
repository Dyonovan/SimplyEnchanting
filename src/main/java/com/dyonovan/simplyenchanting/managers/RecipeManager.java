package com.dyonovan.simplyenchanting.managers;

import com.dyonovan.simplyenchanting.SimplyEnchanting;
import com.dyonovan.simplyenchanting.lib.EnchantmentRecipe;
import com.google.gson.reflect.TypeToken;
import com.teambr.bookshelf.helper.LogHelper;
import com.teambr.bookshelf.util.JsonUtils;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This file was created for SimplyEnchanting
 * <p>
 * SimplyEnchanting is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/13/2017
 */
public class RecipeManager {

    public static ArrayList<EnchantmentRecipe> enchantmentRecipes;

    public static void preInit() {

        if (!loadFromFile())
            generateDefaults();


    }

    private static boolean loadFromFile() {
        LogHelper.logger.info("[SimplyEnchanting] Loading Enchantment json...");
        enchantmentRecipes = JsonUtils.readFromJson(getTypeToken(), SimplyEnchanting.configFolderLocation + File.separator + "recipes.json");
        if (enchantmentRecipes == null)
            enchantmentRecipes = new ArrayList<>();
        return !enchantmentRecipes.isEmpty();
    }

    private static TypeToken<ArrayList<EnchantmentRecipe>> getTypeToken() {
        return new TypeToken<ArrayList<EnchantmentRecipe>>() {};
    }

    private static void generateDefaults() {

        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("protection").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("protection").getMaxLevel(), "minecraft:obsidian"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("fire_protection").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("fire_protection").getMaxLevel(), "minecraft:magma_cream"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("feather_falling").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("feather_falling").getMaxLevel(), "minecraft:feather"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("blast_protection").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("blast_protection").getMaxLevel(), "minecraft:gunpowder"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("projectile_protection").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("projectile_protection").getMaxLevel(), "minecraft:arrow"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("respiration").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("respiration").getMaxLevel(), "minecraft:glass_bottle"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("aqua_affinity").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("aqua_affinity").getMaxLevel(), "minecraft:waterlily"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("thorns").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("thorns").getMaxLevel(), "minecraft:cactus"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("depth_strider").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("depth_strider").getMaxLevel(), "minecraft:iron_ingot"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("frost_walker").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("frost_walker").getMaxLevel(), "minecraft:ice"));
        //enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("binding_curse").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("binding_curse").getMaxLevel(), "minecraft:obsidian"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("sharpness").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("sharpness").getMaxLevel(), "minecraft:quartz"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("smite").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("smite").getMaxLevel(), "minecraft:rotten_flesh"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("bane_of_arthropods").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("bane_of_arthropods").getMaxLevel(), "minecraft:spider_eye"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("knockback").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("knockback").getMaxLevel(), "minecraft:piston"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("fire_aspect").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("fire_aspect").getMaxLevel(), "minecraft:blaze_powder"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("looting").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("looting").getMaxLevel(), "minecraft:skull"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("sweeping").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("sweeping").getMaxLevel(), "minecraft:tnt"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("efficiency").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("efficiency").getMaxLevel(), "minecraft:emerald"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("silk_touch").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("silk_touch").getMaxLevel(), "minecraft:diamond"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("unbreaking").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("unbreaking").getMaxLevel(), "minecraft:slime_ball"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("fortune").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("fortune").getMaxLevel(), "minecraft:gold_ingot"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("power").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("power").getMaxLevel(), "minecraft:flint"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("punch").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("punch").getMaxLevel(), "minecraft:ender_eye"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("flame").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("flame").getMaxLevel(), "minecraft:flint_and_steel"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("infinity").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("infinity").getMaxLevel(), "minecraft:ender_pearl"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("luck_of_the_sea").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("luck_of_the_sea").getMaxLevel(), "minecraft:dye:0"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("lure").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("lure").getMaxLevel(), "minecraft:fish"));
        enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("mending").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("mending").getMaxLevel(), "minecraft:end_stone"));
        //enchantmentRecipes.add(new EnchantmentRecipe(Enchantment.getEnchantmentByLocation("vanishing_curse").getRegistryName().toString(), Enchantment.getEnchantmentByLocation("vanishing_curse").getMaxLevel(), "minecraft:obsidian"));

        saveToFile();
    }

    private static void saveToFile() {
        if (!enchantmentRecipes.isEmpty())
            JsonUtils.writeToJson(enchantmentRecipes, SimplyEnchanting.configFolderLocation + File.separator + "recipes.json");
    }

    public static EnchantmentRecipe isItemValid(ItemStack stack) {
        for (EnchantmentRecipe enchantmentRecipe : enchantmentRecipes) {
            List<String> itemString = Arrays.asList(enchantmentRecipe.eItem.split(":"));
            Block block = Block.getBlockFromName(itemString.get(0) + ":" + itemString.get(1));
            if (block != null) {
                if (stack.isItemEqual(itemString.size() == 2 ? new ItemStack(block) : new ItemStack(block, 1, Integer.parseInt(itemString.get(2)))))
                    return enchantmentRecipe;
            }
            Item item = Item.getByNameOrId(itemString.get(0) + ":" + itemString.get(1));
            if (item != null) {
                if (stack.isItemEqual(itemString.size() == 2 ? new ItemStack(item) : new ItemStack(item, 1, Integer.parseInt(itemString.get(2)))))
                    return enchantmentRecipe;
            }
        }
        return null;
    }

}
