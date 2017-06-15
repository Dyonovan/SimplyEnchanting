package com.dyonovan.simplyenchanting;

import com.dyonovan.simplyenchanting.common.CommonProxy;
import com.dyonovan.simplyenchanting.lib.Reference;
import com.dyonovan.simplyenchanting.managers.BlockManager;
import com.dyonovan.simplyenchanting.managers.ConfigManager;
import com.dyonovan.simplyenchanting.managers.GuiHandler;
import com.dyonovan.simplyenchanting.managers.RecipeManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@SuppressWarnings("unused")
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class SimplyEnchanting
{

    @Instance
    public static SimplyEnchanting INSTANCE;

    //The logger. For logging
    public static final Logger logger = LogManager.getLogger(Reference.MOD_NAME);

    public static String configFolderLocation;

    @SidedProxy(clientSide = "com.dyonovan.simplyenchanting.client.ClientProxy",
            serverSide = "com.dyonovan.simplyenchanting.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabSimplyEnchanting = new CreativeTabs("tabSimplyEnchanting") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.ENCHANTING_TABLE);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        configFolderLocation = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID;

        ConfigManager.preInit();
        BlockManager.preInit();
        RecipeManager.preInit();

        proxy.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

    }
}
