package pl.jbujak.ecommerse.catalog.Sales;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class SalesTest {

    @Test
    void itShowsOffer(){
        SalesFacade sales = thereIsSalesFacade();
        String customerId =  thereIsExampleCustomer("Kuba");

        //Offer offer = sales.getCurrentOffer(customerId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0,offer.getItemsCount());
        assertEquals(BigDecimal.ZERO , offer.getTotal());
    }

    private String thereIsExampleCustomer(String id) {
    return id;
    }

    private SalesFacade thereIsSalesFacade() {
        return new SalesFacade();
    }

    @Test
    public void itAllowsToAddProductTocard(){

    }

    @Test
    public void itAllowsAddToCard(){

    }

    @Test
    public void itALlowsToAcceptOffer(){

    }
}

