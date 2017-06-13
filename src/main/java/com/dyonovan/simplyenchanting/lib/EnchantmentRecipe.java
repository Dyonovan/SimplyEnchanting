package com.dyonovan.simplyenchanting.lib;

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
public class EnchantmentRecipe {

    public String eName;
    public int eMaxLevel;
    public String eItem;
    //public int eLapisPerLevel;
    //public int eExp;

    public EnchantmentRecipe(String name, int max, String item) {
        this.eName = name;
        this.eMaxLevel = max;
        this.eItem = item;
        //this.eLapisPerLevel = lapis;
        //this.eExp = exp;
    }
}
