/*
 * BukkitHandler.java
 * Project: AuctionHouse
 * 
 * Programmed by: Matthew Paul <mpaul0416@gmail.com>
 * Date created: Jan 17, 2013
 * Last modified by: Matthew Paul <mpaul0416@gmail.com>
 * 
 * TODO Add a meaningful file header
 */


package auction;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Provides the interface between the auction house plugin and the server.
 * @author Matthew Paul <mpaul0416@gmail.com>
 */
public class BukkitHandler {

    /**
     * The owning plugin.
     */
    private Plugin plugin;
    /**
     * The economy object to use.
     */
    private Economy economy;
    
    /**
     * Create a new handler for this plugin.
     * @param plugin The owning plugin.
     */
    public BukkitHandler(Plugin plugin) {
        this.plugin = plugin;
        initializeEconomy();
    }
    
    /**
     * Log the error to the appropriate log.
     * @param error The error to log.
     */
    public void logError(String error)
    {
        plugin.getLogger().severe(error);
    }
    
    public double getAmount(AuctionPlayer player)
    {
        return economy.getBalance(player.getPlayer().getName());
    }
    
    /**
     * Add the amount to the specified player.
     * @param player The player to add the money to.
     * @param amount The amount to add. Must be positive.
     * @throws TransactionFailureException If the transaction failed. Check the error message for details.
     * @throws IllegalArgumentException If the amount to add is negative.
     */
    public void addMoney(AuctionPlayer player, double amount) throws TransactionFailureException
    {
        if (amount >= 0) {
            EconomyResponse response = economy.depositPlayer(player.getPlayer().getName(), amount);
            
            if (!response.transactionSuccess())
            {
                throw new TransactionFailureException(response.errorMessage);
            }
        }
        else {
            throw new IllegalArgumentException(String.format("Negative amounts not allowed: %s", amount));
        }
    }
    
    /**
     * Withdraw the amount of money from the specified player.
     * @param player The player to withdraw the money from.
     * @param amount The amount to withdraw
     * @throws TransactionFailureException If the transaction failed for some reason.
     * Check the error message for details.
     * @throws IllegalArgumentException If the amount to withdraw is negative.
     */
    public void withdrawMoney(AuctionPlayer player, double amount) throws TransactionFailureException {
        if (amount >= 0) {
            EconomyResponse response = economy.withdrawPlayer(player.getPlayer().getName(), amount);
            
            if (!response.transactionSuccess()) {
                throw new TransactionFailureException(response.errorMessage);
            }
        }
        else {
            throw new IllegalArgumentException(String.format("Negative amounts not allowed: %s", amount));
        }
        
    }
    
    /**
     * Initialize the economy portion of this bukkit handler.
     * @throws IllegalStateException If economy intialization failed. See message
     * for more details.
     */
    private void initializeEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            throw new IllegalStateException("Vault plugin not found!");
        }
        RegisteredServiceProvider<Economy> provider =
                plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (provider == null) {
            throw new IllegalStateException("Service provider not found.");
        }
        economy = provider.getProvider();
        if (economy == null) {
            throw new IllegalStateException("Economy can't be initialized.");
        }
    }
}
