package com.dyonovan.simplyenchanting.client;

import com.dyonovan.simplyenchanting.common.CommonProxy;
import com.dyonovan.simplyenchanting.common.tiles.TileEnchantment;
import com.dyonovan.simplyenchanting.managers.BlockManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;

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
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        ItemRenderManager.registerBlockModel(BlockManager.blockEnchantment, "blockEnchantment", "normal");
    }

    @Override
    public void init() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnchantment.class, new TileEntityEnchantmentRenderer());
    }

    @Override
    public void postInit() {}
}
