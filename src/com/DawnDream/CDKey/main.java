package com.DawnDream.CDKey;

import com.DawnDream.CDKey.commands.mainCommand;
import com.DawnDream.CDKey.commands.reload;
import com.DawnDream.CDKey.config.ConfigIO.CDKs;
import com.DawnDream.CDKey.events.close;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import sun.applet.Main;

import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin {

    public static JavaPlugin instance;
    @Override
    public void onLoad(){
        saveConfig();
    }

    @Override
    public void onEnable(){
        instance = this;
        System.out.println(ChatColor.RED + "[CDKey] Plugin OnEnable!");
        Bukkit.getPluginCommand("cdk").setExecutor(new mainCommand());
        Bukkit.getPluginCommand("cdk").setTabCompleter(new mainCommand());
        Bukkit.getPluginCommand("cdkReload").setExecutor(new reload());
        Bukkit.getPluginManager().registerEvents(new close() , this);
        CDKs.onLoad();
        System.out.println(CDKs.getList("CDKs.name"));
    }
}
