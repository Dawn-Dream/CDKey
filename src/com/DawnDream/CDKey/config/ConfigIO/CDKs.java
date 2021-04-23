package com.DawnDream.CDKey.config.ConfigIO;

import com.DawnDream.CDKey.main;
import com.sun.istack.internal.NotNull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class CDKs {
    public static File mainFile = new File(main.instance.getDataFolder() , "\\config");
    public static File file = new File(mainFile.getAbsolutePath() , "\\CDKs.yml");
    public static FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

    public static void onLoad(){
        if (!mainFile.exists()){
            mainFile.mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入
     * */

    /** @deprecated */
    public static void writeFile(String path , String str){
        fileConfiguration.set(path , str);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 写入Object */
    public static void set(String path , Object object){
        fileConfiguration.set(path , object);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 写入ItemStack */
    public static void setItemStack(String path , ItemStack itemStack){
        fileConfiguration.set(path , itemStack);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 写入String */
    public static void setString(String path , String str){
        fileConfiguration.set(path , str);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 写入List */
    public static void setList(String path , List list){
        fileConfiguration.set(path , list);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 写入Map */
    public static void setMap(String path , Map map){
        fileConfiguration.set(path , map);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取
     * */

    /** @deprecated */
    public static String getFile(String path){
        return (String) fileConfiguration.get(path);
    }

    /** 读取path中的String */
    public static String getString(String path){
        return (String) fileConfiguration.get(path);
    }

    /** 读取path中的ItemStack */
    public static ItemStack getItemStack(String path){
        return fileConfiguration.getItemStack(path);
    }

    /** 读取path中的List */
    public static List getList(String path){
        if (fileConfiguration.getList(path) == null){
            List n = new ArrayList<>();
            CDKs.set(path , n);
            return null;
        }
        return fileConfiguration.getList(path);
    }

    /** 读取path中的List */
    public static Object get(String path){
        return fileConfiguration.get(path);
    }

    /** 读取path中的Int */
    public static Object getInt(String path){
        return fileConfiguration.getInt(path);
    }



    /** CDK是否存在 */
    public static boolean hasCDKs(String key){
        if (fileConfiguration.getList("CDKs.nameList") == null){
            List n = new ArrayList<>();
            CDKs.set("CDKs.nameList" , n);
        }
        ArrayList<String> list = (ArrayList<String>) CDKs.get("CDKs.nameList");
        for (int i = 0 ; i <= list.size() - 1 ; i++){
            if (key.equals(getString("CDK." + list.get(i) + ".key"))){
                return true;
            }
        }
        return false;
    }

    /** CDK是否存在 */
    public static boolean hasCDKsName(String name){
        if (fileConfiguration.getList("CDKs.nameList") == null){
            List n = new ArrayList<>();
            CDKs.set("CDKs.nameList" , n);
        }
        ArrayList<String> list = (ArrayList<String>) CDKs.get("CDKs.nameList");
        for (int i = 0 ; i <= list.size() - 1 ; i++){
            if (name.equals(getString("CDK." + list.get(i) + ".name"))){
                return true;
            }
        }
        return false;
    }

    /** 获取CDK的name */
    public static String getCDKsName(String key){
        if (fileConfiguration.getList("CDKs.nameList") == null){
            List n = new ArrayList<>();
            CDKs.set("CDKs.nameList" , n);
        }
        ArrayList<String> list = (ArrayList<String>) CDKs.get("CDKs.nameList");
        for (int i = 0 ; i <= list.size() - 1 ; i++){
            if (key.equals(getString("CDK." + list.get(i) + ".key"))){
                return list.get(i);
            }
        }
        return null;
    }




    /**
     * CDK次数
     * */


    /** CDK次数获取 */
    public static int getCDKsFrequency(String name){
        return fileConfiguration.getInt("CDK." + name + ".frequency");
    }

    /** CDK次数设置 */
    public static void setCDKsFrequency(String name , @Nullable Object frequency){
        fileConfiguration.set("CDK." + name + ".frequency" , frequency);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 检测CDK次数是否为 0*/
    public static boolean isCDKsNull(String cdk){
        if (getCDKsFrequency(cdk) == 0){
            return true;
        }
        return false;
    }


    /**
     * CDK setting
     * */

    public static void setkey(String name , String key){
        fileConfiguration.set("CDK." + name + ".key" , key);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
