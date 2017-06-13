package com.dyonovan.simplyenchanting.common.tiles;

import com.dyonovan.simplyenchanting.managers.RecipeManager;
import com.teambr.bookshelf.common.tiles.InventoryHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

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
public class TileEnchantment extends InventoryHandler {

    public final int ITEM_SLOT = 0;
    public final int LAPIS_SLOT = 1;
    public final int BOOK_SLOT = 2;
    public final int OUTPUT_SLOT = 3;

    public int tickCount;
    public float pageFlip;
    public float pageFlipPrev;
    private float flipT;
    private float flipA;
    public float bookSpread;
    public float bookSpreadPrev;
    public float bookRotation;
    public float bookRotationPrev;
    private float tRot;
    private static final Random rand = new Random();

    @Override
    public void update() {
        flipBook();
    }

    private void flipBook() {
        this.bookSpreadPrev = this.bookSpread;
        this.bookRotationPrev = this.bookRotation;
        EntityPlayer entityplayer = this.world.getClosestPlayer((double) ((float) this.pos.getX() + 0.5F), (double) ((float) this.pos.getY() + 0.5F), (double) ((float) this.pos.getZ() + 0.5F), 3.0D, false);

        if (entityplayer != null) {
            double d0 = entityplayer.posX - (double) ((float) this.pos.getX() + 0.5F);
            double d1 = entityplayer.posZ - (double) ((float) this.pos.getZ() + 0.5F);
            this.tRot = (float) MathHelper.atan2(d1, d0);
            this.bookSpread += 0.1F;

            if (this.bookSpread < 0.5F || rand.nextInt(40) == 0) {
                float f1 = this.flipT;

                while (true) {
                    this.flipT += (float) (rand.nextInt(4) - rand.nextInt(4));

                    if (f1 != this.flipT) {
                        break;
                    }
                }
            }
        } else {
            this.tRot += 0.02F;
            this.bookSpread -= 0.1F;
        }

        while (this.bookRotation >= (float) Math.PI) {
            this.bookRotation -= ((float) Math.PI * 2F);
        }

        while (this.bookRotation < -(float) Math.PI) {
            this.bookRotation += ((float) Math.PI * 2F);
        }

        while (this.tRot >= (float) Math.PI) {
            this.tRot -= ((float) Math.PI * 2F);
        }

        while (this.tRot < -(float) Math.PI) {
            this.tRot += ((float) Math.PI * 2F);
        }

        float f2;

        for (f2 = this.tRot - this.bookRotation; f2 >= (float) Math.PI; f2 -= ((float) Math.PI * 2F)) {
            ;
        }

        while (f2 < -(float) Math.PI) {
            f2 += ((float) Math.PI * 2F);
        }

        this.bookRotation += f2 * 0.4F;
        this.bookSpread = MathHelper.clamp(this.bookSpread, 0.0F, 1.0F);
        ++this.tickCount;
        this.pageFlipPrev = this.pageFlip;
        float f = (this.flipT - this.pageFlip) * 0.4F;
        float f3 = 0.2F;
        f = MathHelper.clamp(f, -0.2F, 0.2F);
        this.flipA += (f - this.flipA) * 0.9F;
        this.pageFlip += this.flipA;
    }

    @Override
    protected int getInventorySize() {
        return 4;
    }

    @Override
    protected boolean isItemValidForSlot(int index, ItemStack stack) {
        switch (index) {
            case 0:
                return RecipeManager.isItemValid(stack) != null;
            case 1:
                return stack.isItemEqual(new ItemStack(Items.DYE, 1, 4));
            case 2:
                return stack.isItemEqual(new ItemStack(Items.BOOK));
            default:
                return false;

        }
    }

    @Override
    public void setVariable(int id, double value) {

    }

    @Override
    public Double getVariable(int id) {
        return null;
    }
}
