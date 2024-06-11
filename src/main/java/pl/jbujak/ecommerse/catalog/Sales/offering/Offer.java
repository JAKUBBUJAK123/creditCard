package pl.jbujak.ecommerse.catalog.Sales.offering;

import java.math.BigDecimal;

public class Offer {
    private BigDecimal total;
    private int itemsCount;

    public Offer(BigDecimal total, int itemsCount) {
        this.total = total;
        this.itemsCount = itemsCount;
    }

    public Object getItemsCount() {
        return itemsCount;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
