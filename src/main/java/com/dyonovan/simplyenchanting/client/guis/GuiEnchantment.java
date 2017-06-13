package com.dyonovan.simplyenchanting.client.guis;

import com.dyonovan.simplyenchanting.common.Containers.ContainerEnchantment;
import com.teambr.bookshelf.client.gui.GuiBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

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
public class GuiEnchantment<C extends ContainerEnchantment> extends GuiBase<C> {


    /**
     * Main constructor for Guis
     *
     * @param inventory The container
     * @param width     The width of the gui
     * @param height    The height of the gui
     * @param title     The title of the gui
     * @param texture   The location of the background texture
     */
    public GuiEnchantment(C inventory, int width, int height, String title, ResourceLocation texture) {
        super(inventory, width, height, title, texture);
    }

    @Override
    protected void addComponents() {

    }
}
