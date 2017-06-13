package com.dyonovan.simplyenchanting.common.Containers;

import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import com.dyonovan.simplyenchanting.managers.RecipeManager;
import com.teambr.bookshelf.common.container.BaseContainer;
import com.teambr.bookshelf.util.WorldUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

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
public class ContainerEnchantment extends BaseContainer {

    public IInventory enchantInventory;
    private TileEnchantment tile;
    private final World world;

    public ContainerEnchantment(EntityPlayer player, TileEnchantment inventory) {
        super(player.inventory, inventory);

        this.world = player.getEntityWorld();
        this.tile = inventory;

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

        addSlotToContainer(new Slot(enchantInventory, inventory.ITEM_SLOT, 31, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return RecipeManager.isItemValid(stack) != null;
            }
        });
        addSlotToContainer(new Slot(enchantInventory, inventory.LAPIS_SLOT, 80, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.isItemEqual(new ItemStack(Items.DYE, 1, 4));
            }
        });
        addSlotToContainer(new Slot(enchantInventory, inventory.BOOK_SLOT, 129, 13) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.isItemEqual(new ItemStack(Items.BOOK));
            }
        });
        addSlotToContainer(new Slot(enchantInventory, inventory.OUTPUT_SLOT, 80, 49) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventorySlots(84);
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

    /*@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        Slot slot = inventorySlots.get(slotId);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemToTransfer = slot.getStack();
            ItemStack copy = itemToTransfer.copy();
            if (slotId < inventorySize)
                if (!mergeItemStack(itemToTransfer, inventorySize, inventorySlots.size(), true)) return null;
            else if (!mergeItemStack(itemToTransfer, 0, inventorySize, false))
                return null;
            slot.onSlotChanged();
            if (itemToTransfer.getCount() != copy.getCount()) {
                slot.onTake(player, copy);
                return copy;
            }
        }
        return null;
    }*/
}
