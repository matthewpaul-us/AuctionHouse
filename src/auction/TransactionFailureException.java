/*
 * TransactionFailureException.java
 * Project: AuctionHouse
 * 
 * Programmed by: Matthew Paul <mpaul0416@gmail.com>
 * Date created: Jan 17, 2013
 * Last modified by: Matthew Paul <mpaul0416@gmail.com>
 * 
 * Error that is thrown when a transaction goes wrong.
 */


package auction;

/**
 * Thrown if there was an error in the transaction.
 * @author Matthew Paul <mpaul0416@gmail.com>
 */
class TransactionFailureException extends Exception {

    public TransactionFailureException(String errorMessage) {
        super(errorMessage);
    }

}
