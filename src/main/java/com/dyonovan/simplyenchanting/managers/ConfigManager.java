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

    public static int lapisModifier;
    public static int xpModifier;

    public static void preInit() {

        config.load();

        lapisModifier = config.getInt("Lapis Modifier", "Modifiers", 8, 1, 64, "Lapis required per Enchant Level");
        xpModifier = config.getInt("XP Modifier", "Modifiers", 10, 1, 64, "XP Levels required per Enchant Level");

        config.save();

    }
}
