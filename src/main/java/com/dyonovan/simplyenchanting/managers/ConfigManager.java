package com.dyonovan.simplyenchanting.managers;

import com.dyonovan.simplyenchanting.SimplyEnchanting;
import com.dyonovan.simplyenchanting.lib.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * This file was created for SimplyEnchanting
 * <p>
 * SimplyEnchanting is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/13/2017
 */
public class ConfigManager {

    public static Configuration config = new Configuration(new File(SimplyEnchanting.configFolderLocation + File.separator + Reference.MOD_ID + ".cfg"));

    public static void preInit() {
        /*File dir = new File(SimplyEnchanting.configFolderLocation);
        if (!dir.exists())
            dir.mkdir();*/
        config.load();

        config.save();

    }
}
