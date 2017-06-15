package com.dyonovan.simplyenchanting.Utils;

import com.dyonovan.simplyenchanting.SimplyEnchanting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;

/**
 * This file was created for SimplyEnchanting
 * <p>
 * SimplyEnchanting is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 6/15/2017
 */
public class JsonUtils {
    public static boolean writeToJson(Object toWrite, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(toWrite);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);
            writer.close();
            return true;
        } catch (IOException e) {
            SimplyEnchanting.logger.warn("Failed to write to: " + path);
            return false;
        }
    }

    public static <C> C readFromJson(TypeToken type, String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            return gson.fromJson(reader, type.getType());
        } catch (FileNotFoundException e) {
            SimplyEnchanting.logger.warn("Could not find file: " + path);
            return null;
        }
    }
}
