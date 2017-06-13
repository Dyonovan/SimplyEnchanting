package com.dyonovan.simplyenchanting.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * This file was created for SimplyEnchanting
 * <p>
 * SimplyEnchanting is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/11/2017
 */
public class ItemRenderManager {

    /**
     * Registers all item models for our items
     */
    public static void registerItemRenderer() {

    }

    /**
     * Registers an item model
     * @param item The item
     */
    public static void registerItem(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(new ResourceLocation(item.getRegistryName().toString().toLowerCase()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(new ResourceLocation(item.getRegistryName().toString().toLowerCase()), "inventory"));
    }

    /**
     * Short hand for registerBlockModel with default meta
     */
    public static void registerBlockModel(Block block, String name, String variants) {
        registerBlockModel(block, name, variants, 0);
    }

    /**
     * Used to set a block item to reflect to the world model
     * @param block The model
     * @param name The name
     * @param variants The variants
     * @param meta The meta
     */
    public static void registerBlockModel(Block block, String name, String variants, int meta) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                meta, new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString().toLowerCase()), variants));
    }
}
