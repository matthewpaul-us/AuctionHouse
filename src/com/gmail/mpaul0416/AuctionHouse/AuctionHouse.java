/*
 * AuctionHouse.java
 * Project: AuctionHouse
 * 
 * Programmed by: Matthew Paul <mpaul0416@gmail.com>
 * Date created: Jan 17, 2013
 * Last modified by: Matthew Paul <mpaul0416@gmail.com>
 * 
 * TODO Add a meaningful file header
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.mpaul0416.AuctionHouse;

import auction.BukkitHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Matthew Paul <mpaul0416@gmail.com>
 */
public class AuctionHouse extends JavaPlugin {
    
    private BukkitHandler handler;

    @Override
    public void onDisable() {
        super.onDisable(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onEnable() {
        handler = new BukkitHandler(this);
    }

    /**
     * Get the handler associated with this plugin.
     * @return The Bukkit Handler to use.
     */
    public BukkitHandler getHandler() {
        return handler;
    }

    
}
