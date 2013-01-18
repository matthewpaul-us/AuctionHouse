/*
 * AuctionPlayer.java
 * Project: AuctionHouse
 * 
 * Programmed by: Matthew Paul <mpaul0416@gmail.com>
 * Date created: Jan 11, 2013
 * Last modified by: Matthew Paul <mpaul0416@gmail.com>
 * 
 * TODO Add a meaningful file header
 */

package auction;

import com.gmail.mpaul0416.AuctionHouse.AuctionHouse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A player associated with an auction.
 *
 * @author Matthew Paul <mpaul0416@gmail.com>
 */
public class AuctionPlayer {

    private Player player;
    private double money;
    private BukkitHandler handler;

    /**
     * Get the value of player
     *
     * @return the value of player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Create a player for an auction with the specified bukkit player and money.
     *
     * @param player The player to create the Auction Player from.
     * @param money The amount of money the player has.
     * @param handler the value of handler
     */
    
    public AuctionPlayer(Player player, double money, BukkitHandler handler) {
        this.player = player;
        this.money = money;
        this.handler = handler;
    }

    /**
     * Get the bukkit name for the player.
     * @return The name of the player, as identified by bukkit.
     */
    public String getName() {
        return player.getName();
    }
    
    /**
     * Set the value of player
     *
     * @param player new value of player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the value of money
     *
     * @return the value of money
     */
    public double getMoney() {
        return money;
    }

    /**
     * Set the value of money
     *
     * @param money new value of money
     */
    public void setMoney(double money) {
        this.money = money;
    }
    
    
    /**
     * Place a bid of the specified amount in the specified auction.
     * @param amount Amount to bid.
     * @param auction Amount to auction.
     * @throws AuctionException if bid is less than allowed.
     */
    public void bid(double amount, Auction auction) throws AuctionException
    {
        auction.addBid(new Bid(new Date(), amount, this));
    }
    
    /**
     * Pay the specified auctionPlayer.
     * @param amount The amount to pay the recipient.
     * @param recipient The AuctionPlayer to pay.
     */
    public void pay(double amount, AuctionPlayer recipient)
    {
        double amountBefore = 0;
        try {
            amountBefore = handler.getAmount(this);
            handler.withdrawMoney(this, amount);
            handler.addMoney(recipient, amount);
        } catch (TransactionFailureException ex) {
            try {
                handler.logError("Failed with error: " + ex.getMessage() + ". Restoring ");
                handler.addMoney(this, amountBefore);
            } catch (TransactionFailureException ex1) {
                handler.logError(String.format("Failed to restore %s's amount to %f", this.getName(), amountBefore));
            }
        }
    }
    
    public void auctionItem(ItemStack item)
    {
        
    }
}
