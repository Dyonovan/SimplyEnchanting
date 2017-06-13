package com.dyonovan.simplyenchanting.common.blocks;

import com.dyonovan.simplyenchanting.SimplyEnchanting;
import com.dyonovan.simplyenchanting.client.guis.GuiEnchantment;
import com.dyonovan.simplyenchanting.common.Containers.ContainerEnchantment;
import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import com.dyonovan.simplyenchanting.lib.Reference;
import com.teambr.bookshelf.Bookshelf;
import com.teambr.bookshelf.common.IOpensGui;
import com.teambr.bookshelf.util.WorldUtils;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

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
public class BlockEnchantment extends BlockEnchantmentTable implements IOpensGui {

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
        if (!playerIn.isSneaking()) {
            playerIn.openGui(Bookshelf.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(new BlockPos(x, y, z)) instanceof TileEnchantment) {
            TileEnchantment tileEnchantment = (TileEnchantment) world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerEnchantment(player, tileEnchantment);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(new BlockPos(x, y, z)) instanceof TileEnchantment) {
            TileEnchantment tileEnchantment = (TileEnchantment) world.getTileEntity(new BlockPos(x, y, z));
            return new GuiEnchantment(new ContainerEnchantment(player, tileEnchantment), 175, 165, "simplyenchanting:guiEnchantment", new ResourceLocation(Reference.MOD_ID, "textures/gui/guienchanting.png"));
        }
        return null;
    }
}
