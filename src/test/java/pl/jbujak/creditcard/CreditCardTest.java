package pl.jbujak.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {
    @Test
    void itAssignCredit() {
        var card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));
        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }

    @Test
    void itAssignCreditv2() {
        var card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1500));
        assertEquals(BigDecimal.valueOf(1500),card.getBalance());
    }
    @Test
    void itDenyCreditBelowThreshold() {
        var card = new CreditCard();

        assertThrows(
                creditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(10))
        );

        try{
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        }catch (creditBelowThresholdException e){
            assertTrue(true);
        }
    }
    @Test
    void itDenyCreditReassignment() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));
        assertThrows(
               CreditAlreadyAssignedException.class,
                () -> card.assignCredit(BigDecimal.valueOf(1200))
        );
    }

    @Test
    void itAllowsToPayForSomething() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(900));
        card.pay(BigDecimal.valueOf(100));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.pay(BigDecimal.valueOf(900))
        );

        assertEquals(
                BigDecimal.valueOf(800),
                card.getBalance()
        );
    }
}
