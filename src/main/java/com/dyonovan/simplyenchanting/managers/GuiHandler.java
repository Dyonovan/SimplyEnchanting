package com.dyonovan.simplyenchanting.managers;

import com.dyonovan.simplyenchanting.client.guis.GuiEnchantment;
import com.dyonovan.simplyenchanting.common.Containers.ContainerEnchantment;
import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

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
public class GuiHandler implements IGuiHandler {

    public static final int ENCHANTMENT_GUI = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ENCHANTMENT_GUI) {
            if (world.getTileEntity(new BlockPos(x, y, z)) instanceof TileEnchantment) {
                TileEnchantment tile = (TileEnchantment) world.getTileEntity(new BlockPos(x, y, z));
                return new ContainerEnchantment(player, tile);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ENCHANTMENT_GUI) {
            if (world.getTileEntity(new BlockPos(x, y, z)) instanceof TileEnchantment) {
                TileEnchantment tile = (TileEnchantment) world.getTileEntity(new BlockPos(x, y, z));
                return new GuiEnchantment(new ContainerEnchantment(player, tile), player);
            }
        }
        return null;
    }
}
