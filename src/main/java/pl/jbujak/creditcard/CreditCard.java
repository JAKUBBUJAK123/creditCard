package pl.jbujak.creditcard;

import java.math.BigDecimal;

public class CreditCard {

    private BigDecimal creditCard;
    private BigDecimal balance;

    public void assignCredit(BigDecimal creditLimit) {
        if(isaBoolean(creditLimit)) {
            throw new creditBelowThresholdException();
        }

        if (isCreditAssigned()) {
            throw new CreditAlreadyAssignedException();
        }

        this.creditCard = creditLimit;
        this.balance = this.creditCard;



    }

    private boolean isCreditAssigned() {
        return this.creditCard != null;
    }

    private static boolean isaBoolean(BigDecimal creditLimit) {
        return BigDecimal.valueOf(100).compareTo(creditLimit) > 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void pay(BigDecimal money) {
        if (canAfford(money)){
            throw  new NotEneoughMoneyException();
        }
        this.balance = this.balance.subtract(money);
    }

    private boolean canAfford(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) < 0;
    }
}
