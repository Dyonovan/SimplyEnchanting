package com.dyonovan.simplyenchanting.common.containers;

import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import com.dyonovan.simplyenchanting.lib.EnchantmentRecipe;
import com.dyonovan.simplyenchanting.managers.ConfigManager;
import com.dyonovan.simplyenchanting.managers.RecipeManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    public final int XP_MODIFIER = ConfigManager.xpModifier;
    private final int LAPIS_MODIFIER = ConfigManager.lapisModifier;


    public int eLevel = 0;
    private boolean running = false;
    private IInventory enchantInventory;
    private final World world;

    public ContainerEnchantment(EntityPlayer player, TileEnchantment tile) {

        this.world = player.getEntityWorld();
        //this.tile = tile;

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
        crafting.sendWindowProperty(this, 0, this.eLevel);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        //onCraftMatrixChanged(enchantInventory);
        for (IContainerListener icontainerlistener : this.listeners) {
            this.broadcastData(icontainerlistener);
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        switch (id) {
            case 0:
                this.eLevel = data;
                break;
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        if (!inventory.getStackInSlot(0).isEmpty() && !inventory.getStackInSlot(1).isEmpty() && !inventory.getStackInSlot(2).isEmpty() && inventory.getStackInSlot(3).isEmpty() && !running) {
            EnchantmentRecipe recipe = RecipeManager.isItemValid(inventory.getStackInSlot(0));
            eLevel = Math.min(Math.min(recipe.eMaxLevel, inventory.getStackInSlot(0).getCount()), inventory.getStackInSlot(1).getCount() / LAPIS_MODIFIER);
            if (eLevel > 0) {
                ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK);
                Enchantment enchant = Enchantment.getEnchantmentByLocation(recipe.eName);
                ItemEnchantedBook.addEnchantment(itemstack, new EnchantmentData(enchant, eLevel));
                this.enchantInventory.setInventorySlotContents(3, itemstack);
            } else
                reset();
        }

        if ((inventory.getStackInSlot(0).isEmpty() || inventory.getStackInSlot(1).isEmpty() || inventory.getStackInSlot(2).isEmpty() || inventory.getStackInSlot(1).getCount() < LAPIS_MODIFIER)
                && !inventory.getStackInSlot(3).isEmpty() && !running) {
            reset();
        }
    }

    private void reset() {
        enchantInventory.removeStackFromSlot(3);
        eLevel = 0;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        if (slotId == 3 && !enchantInventory.getStackInSlot(3).isEmpty() && player.inventory.getItemStack().isEmpty()) {
            if ((player.experienceLevel < (eLevel * XP_MODIFIER)) && !player.isCreative())
                return ItemStack.EMPTY;

            this.running = true;
            if (!player.isCreative())
                player.addExperienceLevel((eLevel * XP_MODIFIER) * (-1)); //removeExperienceLevel(eLevel * XP_MODIFIER);
            enchantInventory.decrStackSize(0, eLevel);
            enchantInventory.decrStackSize(1, eLevel * LAPIS_MODIFIER);
            enchantInventory.decrStackSize(2, 1);
            eLevel = 0;
            this.running = false;
        } else if (slotId == 0)
            reset();
        ItemStack item = super.slotClick(slotId, dragType, clickTypeIn, player);
        onCraftMatrixChanged(enchantInventory);
        return item;
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
            for (int i = 0; i < this.enchantInventory.getSizeInventory() - 1; ++i)
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
