package pl.jbujak.ecommerse.catalog.Sales;


import org.junit.jupiter.api.Test;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.Offer;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculaotr;

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
        return new SalesFacade(new inMemoryCartStorage(), new OfferCalculaotr());
    }

    @Test
    public void itAllowsToAddProductToCart(){
        String productId = thereIsProduct("Example" , BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Kuba");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId,productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1,offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(10) ,offer.getTotal());
    }

    @Test
    public void itAllowsToAddMultipleProductToCart(){
        String productA = thereIsProduct("Example a" , BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example b" , BigDecimal.valueOf(20));
        String customerId = thereIsExampleCustomer("Kuba");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId,productA);
        sales.addToCart(customerId,productB);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1,offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(30) ,offer.getTotal());
    }


    @Test
    public void itAllowsToAddMultipleProductTocardByCustomer(){
        String productA = thereIsProduct("Example a" , BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example b" , BigDecimal.valueOf(20));
        String customerA = thereIsExampleCustomer("Kuba");
        String customerB = thereIsExampleCustomer("Michał");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerA,productA);
        sales.addToCart(customerB,productB);
        Offer offerA = sales.getCurrentOffer(customerA);
        Offer offerB = sales.getCurrentOffer(customerB);

        assertEquals(1,offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(10) ,offerA.getTotal());

        assertEquals(1,offerB.getItemsCount());
        assertEquals(BigDecimal.valueOf(20) ,offerB.getTotal());
    }



    private String thereIsProduct(String example, BigDecimal bigDecimal) {
        return null;
    }

    @Test
    public void itAllowsAddToCard(){

    }

    @Test
    public void itALlowsToAcceptOffer(){

    }
}

