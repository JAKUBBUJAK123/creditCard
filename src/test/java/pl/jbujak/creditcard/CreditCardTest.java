package pl.jbujak.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        assert BigDecimal.valueOf(1500).equals(card.getBalance());
    }
    @Test
    void itDenyCreditBelowThreshold() {
        var card = new CreditCard();
        try{
            card.assignCredit(BigDecimal.valueOf(50));
            assert  false;
        }catch (creditBelowThresholdException e){
            assert true;
        }
    }
}
