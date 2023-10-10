package edu.odu.cs.cs350.tdd;

/**
 * Expresses monetary values in US dollars and cents.
 */
public class Money implements Comparable<Money> {

    private int dollars;
    private int cents;

    /**
     * Create a zero money amount.
     */
    public Money() {
        dollars = 0;
        cents = 0;
    }

    /**
     * Create an Money object worth the given amount.
     * @param theDollars number of dollars
     * @param theCents number of cents
     */
    public Money(int theDollars, int theCents) {
        while (theCents < 0) {
            theCents += 100;
            theDollars -= 1;
        }
        if (theDollars < 0) {
            throw new ArithmeticException("Negative Money amounts are not permitted");
        }
        dollars = theDollars + theCents / 100;
        cents = theCents % 100;
    }

    /**
     * @return the dollar portion
     */
    public int getDollars() {
        return dollars;
    }

    /**
     * @return the cents portion
     */
    public int getCents() {
        return cents;
    }

    /**
     * Render as a string.
     * @return the conventional string representation of US currency
     */
    public String toString() {
        return String.format("$%d.%02d", dollars, cents);
    }

    /**
     * equality test
     * @param obj any object
     * @return true if both objects ae Money and the amounts are equal.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Money) {
            Money right = (Money)obj;
            return (dollars == right.dollars) && (cents == right.cents);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 27277 * cents + 19891 * dollars;
    }

    /**
     * Compare two Money amounts.
     * @param right the other amount
     * @return a negative number if this amount is less than right,
     *    zero if they are equal, or a positive number if this amount is
     *    greater than right.
     */
    public int compareTo(Money right) {
        if (dollars != right.dollars) {
            return dollars - right.dollars;
        } else {
            return cents - right.cents;
        }
    }

}
