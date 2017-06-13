package com.dyonovan.simplyenchanting.managers;

import com.dyonovan.simplyenchanting.common.blocks.BlockEnchantment;
import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

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
public class BlockManager {

    public static BlockEnchantment blockEnchantment = new BlockEnchantment();

    public static void preInit() {
        registerBlock(blockEnchantment, TileEnchantment.class);
    }

    /**
     * Helper Method to register a block
     * @param block The block to register
     * @param itemBlock The item block for this block
     * @param tileEntity The tile class
     * @param oreDict The ore dict tag
     * @return The block registered
     */
    private static <T extends Block> T registerBlock(T block, ItemBlock itemBlock,
                                                     @Nullable Class<? extends TileEntity> tileEntity,
                                                     @Nullable String oreDict) {

        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if (tileEntity != null)
            GameRegistry.registerTileEntity(tileEntity, block.getRegistryName().toString());

        if (oreDict != null)
            OreDictionary.registerOre(oreDict, block);

        return block;
    }

    /**
     * Short hand to register a block
     */
    private static <T extends Block> T registerBlock(T block, @Nullable Class<? extends TileEntity> tileEntity) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return registerBlock(block, itemBlock, tileEntity, null);
    }
}
