package com.DawnDream.CDKey.commands;

import com.DawnDream.CDKey.config.ConfigIO.CDKs;
import com.DawnDream.CDKey.main;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Structure;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.DawnDream.CDKey.config.ConfigIO.CDKs.*;


public class mainCommand implements CommandExecutor , TabCompleter{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0 || strings[0].equals("help")){
            commandSender.sendMessage(ChatColor.GOLD + "------ CDKey Help ------");
            return true;
        }

        if (strings.length == 1){
            setCDKsFrequency("awa" , 1);
            if (hasCDKs(strings[0])){
                //commandSender.sendMessage(ChatColor.GREEN + "YES!!!!!!!!");
                if (isCDKsNull(getCDKsName(strings[0]))){
                    commandSender.sendMessage(ChatColor.RED + "[CDKey] This key has been used up , Please contact the administrator for details");
                }else{
                    setCDKsFrequency(getCDKsName(strings[0]) , getCDKsFrequency(getCDKsName(strings[0])) - 1);
                    Player p = main.instance.getServer().getPlayer(commandSender.getName());
                    Inventory inv = Bukkit.createInventory(p, 9, "CDK:" + strings[0]);
                    for (int i = 0 ; i <= 8 ; i++){
                        ItemStack itemStack = getItemStack("CDK." + getCDKsName(strings[0]) + ".item." + i);
                        inv.setItem(i , itemStack);
                    }
                    p.openInventory(inv);
                    //commandSender.sendMessage(String.valueOf(getCDKsFrequency(strings[0]) - 1));
                }
            }
        }


        if (strings[0].equals("new")){
            /** 权限组 */
            if (commandSender.hasPermission("CDKs.admin")) {
                if (strings.length == 3){
                    String name = strings[1];
                    String key = strings[2];

                    if (CDKs.get("CDKs.nameList") == null){
                        List n = new ArrayList<>();
                        CDKs.set("CDKs.nameList", n);
                    }
                    ArrayList<String> list = (ArrayList<String>) CDKs.get("CDKs.nameList");
                    for (int i = 0 ; i <= list.size() - 1 ; i++){
                        if (list.get(i).equals(name)){
                            commandSender.sendMessage(ChatColor.RED + "[CDKey] The name is in the list! Plz use another name.");
                            return true;
                        }
                    }
                    list.add(name);
                    CDKs.setList("CDKs.nameList" , list);
                    CDKs.setString("CDK." + name + ".name" , name);
                    CDKs.setString("CDK." + name + ".key" , key);
                    CDKs.set("CDK." + name + ".frequency" , 1);

                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        Inventory inv = Bukkit.createInventory(player, 9, "Set CDKey:" + name);
                        player.openInventory(inv);
                        // do something
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "[CDKey] You must be a player!");
                        return true;
                    }


                    return true;
                }else{
                    commandSender.sendMessage(ChatColor.RED + "[CDKey] Command is not found!");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "[CDKey] You dont have Permission to do that!");
            }
        }

        if (strings[0].equals("remove")){
            /** 权限组 */
            if (commandSender.hasPermission("CDKs.admin")) {
                if (strings.length == 2){
                    String name = strings[1];
                    ArrayList<String> list = (ArrayList<String>) CDKs.get("CDKs.nameList");
                    list.remove(name);
                    CDKs.set("CDKs.nameList" , list);
                    CDKs.set("CDK." + name , null);
                    commandSender.sendMessage(ChatColor.GREEN + "[CDKey] Remove it !");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "[CDKey] You dont have Permission to do that!");
            }
        }

        if (strings[0].equals("list")){
            /** 权限组 */
            if (commandSender.hasPermission("CDKs.admin")) {
                ArrayList<String> list = (ArrayList<String>) CDKs.get("CDKs.nameList");
                if (list.size() == 0 || list == null){
                    commandSender.sendMessage(ChatColor.RED + "[CDKey] The list is NULL!");
                }else{
                    for (int i = 0 ; i <= list.size() - 1; i ++){
                        commandSender.sendMessage("- " + list.get(i));
                    }
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "[CDKey] You dont have Permission to do that!");
            }
        }

        if ((strings[0].equals("set"))){
            /** 权限组 */

            if (commandSender.hasPermission("CDKs.admin")){
                if (strings.length == 1 || strings.length == 2 || strings.length == 3){
                    commandSender.sendMessage(ChatColor.RED + "[CDKey] Parameter error!");
                    return true;
                }
                String name = strings[1];
                String mode = strings[3];
                if (hasCDKsName(name)){
                    if (strings[2].equals("frequency")){
                        if (strings.length != 4){
                            commandSender.sendMessage(ChatColor.RED + "[CDKey] Parameter error!");
                            return true;
                        }
                        setCDKsFrequency(name , mode);
                        commandSender.sendMessage(ChatColor.GREEN + "[CDKey] Now the number is left:" + get("CDK." + name + ".frequency"));
                        return true;
                    }
                    if (strings[2].equals("key")){
                        if (strings.length != 4){
                            commandSender.sendMessage(ChatColor.RED + "[CDKey] Parameter error!");
                            return true;
                        }
                        setkey(name , mode);
                        commandSender.sendMessage(ChatColor.GREEN + "[CDKey] Now the key is:" + mode);
                        return true;
                    }else{
                        commandSender.sendMessage(ChatColor.RED + "[CDKey] Parameter error!");
                        return true;
                    }

                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        if (strings.length == 1){
            list.add("help");
            list.add("new");
            list.add("remove");
            list.add("list");
            list.add("set");
            list.add("info");
        }
        if (strings[0].equals("new")){
            if (strings.length == 2){
                list.add("[Name] [CDKey]");
            }
            if (strings.length == 3){
                list.add("[CDkey]");
            }
        }
        if (strings[0].equals("remove")){
            ArrayList<String> CDKslist = (ArrayList<String>) CDKs.get("CDKs.nameList");
            return CDKslist;
        }
        if (strings[0].equals("set")){
            ArrayList<String> CDKslist = (ArrayList<String>) CDKs.get("CDKs.nameList");
            return CDKslist;
        }
        if (strings[0].equals("info")){
            ArrayList<String> CDKslist = (ArrayList<String>) CDKs.get("CDKs.nameList");
            return CDKslist;
        }
        return list;
    }
}
