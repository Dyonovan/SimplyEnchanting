package com.dyonovan.simplyenchanting.common.blocks;

import com.dyonovan.simplyenchanting.SimplyEnchanting;
import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import com.dyonovan.simplyenchanting.lib.Reference;
import com.dyonovan.simplyenchanting.managers.GuiHandler;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
public class BlockEnchantment extends BlockEnchantmentTable {

    public BlockEnchantment() {
        super();
        this.setCreativeTab(SimplyEnchanting.tabSimplyEnchanting);
        this.setRegistryName(new ResourceLocation(Reference.MOD_ID, "blockEnchanting"));
        this.setUnlocalizedName(Reference.MOD_ID + ":blockEnchanting");
        this.setHardness(5.0F);
        this.setResistance(2000.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEnchantment();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.isSneaking() && !worldIn.isRemote) {
            playerIn.openGui(SimplyEnchanting.INSTANCE, GuiHandler.ENCHANTMENT_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
