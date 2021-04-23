package com.DawnDream.CDKey.commands;

import com.DawnDream.CDKey.config.ConfigIO.CDKs;
import com.DawnDream.CDKey.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        CDKs.onLoad();
        main.instance.onLoad();
        commandSender.sendMessage(ChatColor.GREEN + "[CDKey]reloading!");
        return true;
    }
}
