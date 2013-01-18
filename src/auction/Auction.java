/*
 * Auction.java
 * Project: AuctionHouse
 * 
 * Programmed by: Matthew Paul <mpaul0416@gmail.com>
 * Date created: Jan 11, 2013
 * Last modified by: Matthew Paul <mpaul0416@gmail.com>
 * 
 * TODO Add a meaningful file header
 */

package auction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;
import org.bukkit.inventory.ItemStack;

/**
 * An auction which handles the specifics of a single auction.
 * @author Matthew Paul <mpaul0416@gmail.com>
 */
class Auction {

    private double startingPrice;
    private double increaseAmount;
    private GregorianCalendar startTime;
    private int duration;
    private ItemStack item;
    private AuctionPlayer itemSeller;
    private ArrayList<AuctionPlayer> buyers;
    private PriorityQueue<Bid> bids;
    private BukkitHandler handler;
    
    public void start()
    {
        
    }
    
    public void stop()
    {
        
    }
    
    /**
     * Add a bid to the auction.
     * @param bid The bid to add.
     * @throws AuctionException if bid was lower than allowed in the auction.
     */
    public void addBid(Bid bid) throws AuctionException
    {
        Bid topBid = bids.peek();
        
        if (topBid == null || 
                bid.getBidAmount() < topBid.getBidAmount() + increaseAmount){
            bids.add(bid);
        }
        else {
            throw new AuctionException("Bid entered lower than top bid!");
        }
    }
    
    /**
     * Report the status of the auction.
     * @return 
     */
    public String reportStatus()
    {
        String formatString = "[%s] Bid: %s Time: %ss left";
        
        String itemString = String.format("%d %s%s", item.getAmount(), 
                item.getType().toString().toLowerCase(),
                (item.getAmount() != 1? "s" : ""));
        
        String bidString = Double.toString(bids.peek().getBidAmount());
        
        GregorianCalendar calendar = (GregorianCalendar)startTime.clone();
        calendar.add(Calendar.SECOND, duration);
        
        String timeLeft = String.valueOf((calendar.getTimeInMillis() - 
                startTime.getTimeInMillis()) / 1000);
        
        return String.format(formatString, itemString, bidString, timeLeft);
    }
    
    
}
