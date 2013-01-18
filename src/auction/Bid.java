/*
 * Bid.java
 * Project: AuctionHouse
 * 
 * Programmed by: Matthew Paul <mpaul0416@gmail.com>
 * Date created: Jan 11, 2013
 * Last modified by: Matthew Paul <mpaul0416@gmail.com>
 * 
 * TODO Add a meaningful file header
 */


package auction;

import java.util.Date;

/**
 * A bid in an auction.
 * @author Matthew Paul <mpaul0416@gmail.com>
 */
class Bid {

    private Date bidTime;
    private double bidAmount;
    private AuctionPlayer bidder;

    public Bid(Date bidTime, double bidAmount, AuctionPlayer bidder) {
        this.bidTime = bidTime;
        this.bidAmount = bidAmount;
        this.bidder = bidder;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public AuctionPlayer getBidder() {
        return bidder;
    }
}
