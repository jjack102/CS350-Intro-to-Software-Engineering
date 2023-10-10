package edu.odu.cs.cs350.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestRegister {

    @Test
    public void testDefault() {
        CashRegister register = new CashRegister();
        assertThat(register.getAmountInTill().toString(), is("$0.00"));
        assertThat(register.getTotalCharge().toString(), is("$0.00"));
    }

    @Test
    public void testConstructor() {
        Money money = new Money(57,0);
        Money charge = new Money(7,40);
        CashRegister register = new CashRegister(money, charge);
        assertThat(register.getAmountInTill().toString(), is("$57.00"));
        assertThat(register.getTotalCharge().toString(), is("$7.40"));
    }

    @Test 
    public void testGetAmountInTill() {
        CashRegister register = new CashRegister();
        register.setTill(new Money(100, 0));
        assertThat(register.getAmountInTill().toString(), is ("$100.00"));
        register.setTill(new Money(5,85));
        assertThat(register.getAmountInTill().toString(), is ("$5.85"));
    }


    @Test 
    public void testAddToTill() {
        CashRegister register = new CashRegister();
        register.addToTill(new Money(50, 22));
        assertThat(register.getAmountInTill().toString(), is ("$50.22"));
        register.addToTill(new Money(38,91));
        assertThat(register.getAmountInTill().toString(), is ("$89.13"));
    }

    
    @Test
    public void testCharge() {
        CashRegister register = new CashRegister();
        register.charge(new Money(5, 95));
        assertThat(register.getTotalCharge().toString(), is("$5.95"));

        CashRegister register2 = new CashRegister();
        register2.charge(new Money(47, 86));
        assertThat(register2.getTotalCharge().toString(), is("$47.86"));
    }

    @Test
    public void testGetTotalCharge() {
        CashRegister register = new CashRegister();
        register.charge(new Money(8, 33));
        assertThat(register.getTotalCharge().toString(), is("$8.33"));
        register.charge(new Money(1, 0));
        assertThat(register.getTotalCharge().toString(), is("$9.33"));
    }

    @Test
    public void testPayment() {
        CashRegister register = new CashRegister();
    
        // test expection not enough money in till
        assertThrows(Exception.class, () -> register.payment(new Money(10, 0)));
        register.addToTill(new Money(1,76));
        register.charge(new Money(10, 70));
        assertThrows(Exception.class, () -> register.payment(new Money(15, 0)));

        // test expection insufficent funds
        assertThrows(Exception.class, () -> register.payment(new Money(1, 0)));

        // test giving back change
        assertThat(register.payment(new Money(10,70)).toString(), is("$0.00"));
        assertThat(register.getAmountInTill().toString(), is("$12.46"));
        assertThat(register.getTotalCharge().toString(), is("$0.00"));

        register.charge(new Money(5, 89));
        register.charge(new Money(23,6));
        assertThat(register.payment(new Money(30, 0)).toString(), is("$1.05"));
        assertThat(register.getAmountInTill().toString(), is("$41.41"));
        assertThat(register.getTotalCharge().toString(), is("$0.00"));
    }

}
