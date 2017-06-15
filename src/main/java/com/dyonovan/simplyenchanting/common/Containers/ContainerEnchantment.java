package com.dyonovan.simplyenchanting.common.Containers;

import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import com.dyonovan.simplyenchanting.managers.RecipeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
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
public class ContainerEnchantment extends Container {

    public IInventory enchantInventory;
    private TileEnchantment tile;
    private final World world;

    public ContainerEnchantment(EntityPlayer player, TileEnchantment tile) {

        this.world = player.getEntityWorld();
        this.tile = tile;

        this.enchantInventory = new InventoryBasic("CustomEnchantBook", true, 4) {
            @Override
            public int getInventoryStackLimit()
            {
                return 64;
            }

            @Override
            public void markDirty()
            {
                super.markDirty();
                ContainerEnchantment.this.onCraftMatrixChanged(this);
            }
        };

        addSlotToContainer(new Slot(enchantInventory, 0, 31, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return RecipeManager.isItemValid(stack) != null;
            }
        });
        addSlotToContainer(new Slot(enchantInventory, 1, 80, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.isItemEqual(new ItemStack(Items.DYE, 1, 4));
            }
        });
        addSlotToContainer(new Slot(enchantInventory, 2, 129, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.isItemEqual(new ItemStack(Items.BOOK));
            }
        });
        addSlotToContainer(new Slot(enchantInventory, 3, 80, 49) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 9; column++)
                addSlotToContainer(new Slot(player.inventory,
                        column + row * 9 + 9,
                        8 + column * 18,
                        84 + row * 18));
        }

        for(int slot = 0; slot < 9; slot++)
            addSlotToContainer(new Slot(player.inventory, slot, 8 + slot * 18, 84 + 58));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        this.broadcastData(listener);
    }

    private void broadcastData(IContainerListener crafting) {

    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        if (!inventory.getStackInSlot(0).isEmpty() && !!inventory.getStackInSlot(1).isEmpty() && !!inventory.getStackInSlot(2).isEmpty()) {

        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < this.enchantInventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = this.enchantInventory.removeStackFromSlot(i);

                if (!itemstack.isEmpty())
                {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        if(index < 0 || index > inventorySlots.size())
            return super.transferStackInSlot(playerIn, index);
        Slot slot = inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack itemToTransfer = slot.getStack();
            ItemStack copy = itemToTransfer.copy();

            if(index < getInventorySizeNotPlayer()) {
                if (!mergeItemStack(itemToTransfer, getInventorySizeNotPlayer(), inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            } else if(!mergeItemStack(itemToTransfer, 0, getInventorySizeNotPlayer(), false))
                return ItemStack.EMPTY;

            /*if(itemToTransfer.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);*/
            else
                slot.onSlotChanged();

            if(itemToTransfer.getCount() != copy.getCount())
                return copy;
        }
        return ItemStack.EMPTY;
    }

    private int getInventorySizeNotPlayer() {
        return 4;
    }
}
