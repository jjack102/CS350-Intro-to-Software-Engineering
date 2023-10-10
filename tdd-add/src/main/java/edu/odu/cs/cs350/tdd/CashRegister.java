package edu.odu.cs.cs350.tdd;

/**
 * A cash register holds cash and does transactions 
 * 
 */
public class CashRegister {
    private Money till;
    private Money totalCharge;

    /**
     * Create an empty till
     */
    public CashRegister() {
       till = new Money();
       totalCharge = new Money();
    }

    /** 
     * ?This story requires a cash register with some money already in 
     * the till and some charges already applied?
     */
    public CashRegister(Money money, Money charge) {
        till = new Money(money.getDollars(), money.getCents());
        totalCharge = new Money(charge.getDollars(), charge.getCents());
    }

    /**
     * set amount in till
     * @param money
     */
    public void setTill(Money money) {
        till = money;
    }

    /**
     * @return the till
     */
    public Money getAmountInTill() {
        return till;
    }

    /**
     * @return the total of all items rung up
     */
    public Money getTotalCharge() {
        return totalCharge;
    }

    /**
     * Add money to the till
     * @param money
     */
    public void addToTill(Money money) {
        till = new Money(till.getDollars() + money.getDollars(), 
                         till.getCents() + money.getCents());
    }

    /**
     * When a new item is rung up, add the amount to the total charge
     * @param money
     */
    public void charge(Money money) {
        totalCharge = new Money(totalCharge.getDollars() + money.getDollars(), 
                                totalCharge.getCents() + money.getCents());
    }


    /**
     * The customer makes a payment. If there is enough money in 
     * the till to make change, then change is taken from the till and
     * the cusomer's payment is put into the till.
     * @param money
     * @return change
     */
    public Money payment(Money payment) {
        Money change = new Money(payment.getDollars() - totalCharge.getDollars(),
                                  payment.getCents() - totalCharge.getCents());
        
        if(till.compareTo(change) < 0) {
            throw new IllegalArgumentException("Not enough change in till");
        }
        if(change.compareTo(new Money()) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
    
        till = new Money(till.getDollars() + totalCharge.getDollars(),
                         till.getCents() + totalCharge.getCents());

        totalCharge = new Money();
        return change;
    }
    
}
