package com.DawnDream.CDKey.events;

import com.DawnDream.CDKey.config.ConfigIO.CDKs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class close implements Listener {
    @EventHandler
    public void close(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        InventoryView inv = p.getOpenInventory();
        String str = inv.getTitle();
        char ch[] = str.toCharArray();
        if (str.contains("Set CDKey:")){
            for (int i = 0 ; i <= ch.length - 1 ; i++){
                if (ch[i] == ':'){
                    str = str.substring(i + 1 , ch.length);
                    break;
                }
            }
            for (int i = 0 ; i <= inventory.getSize() ; i++){
                CDKs.setItemStack("CDK." + str + ".item." + i , inv.getItem(i));
            }
        }


    }
}
