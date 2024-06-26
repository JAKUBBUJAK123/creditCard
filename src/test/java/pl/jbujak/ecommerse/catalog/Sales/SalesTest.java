package pl.jbujak.ecommerse.catalog.Sales;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jbujak.ecommerse.catalog.ProductCatalog;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.Offer;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculator;
import pl.jbujak.ecommerse.catalog.Sales.reservation.ReservationRepository;
import pl.jbujak.ecommerse.catalog.Sales.reservation.SpyPaymentGateway;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
@SpringBootTest
public class SalesTest {
    @Autowired
    ProductCatalog catalog;

    @Test
    void itShowsOffers(){
        SalesFacade sales = thereIsSaleFacade();
        String customerId = thereIsExampleCustomer("Kuba");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private String thereIsExampleCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSaleFacade() {
        return new SalesFacade(
                new inMemoryCartStorage(),
                new OfferCalculator(catalog),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    @Test
    void idAllowsAddToCart(){
        String productId = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Wojtek");
        SalesFacade sales = thereIsSaleFacade();
        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1, offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(10.0), offer.getTotal());
    }

    @Test
    void idAllowsAddToMultipleProductsCart(){
        String productA = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example", "desc", BigDecimal.valueOf(20));
        String customerId = thereIsExampleCustomer("Wojtek");
        SalesFacade sales = thereIsSaleFacade();

        sales.addToCart(customerId, productA);
        sales.addToCart(customerId, productB);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(2, offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(30.0), offer.getTotal());
    }

    @Test
    void itDistinguishCartsByCustomer(){
        String productA = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example", "desc", BigDecimal.valueOf(20));
        String customerA = thereIsExampleCustomer("Wojtek");
        String customerB = thereIsExampleCustomer("Lukasz");
        SalesFacade sales = thereIsSaleFacade();

        sales.addToCart(customerA, productA);
        sales.addToCart(customerB, productB);
        Offer offerA = sales.getCurrentOffer(customerA);
        Offer offerB = sales.getCurrentOffer(customerB);

        assertEquals(1, offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(10.0), offerA.getTotal());

        assertEquals(1, offerB.getItemsCount());
        assertEquals(BigDecimal.valueOf(20.0), offerB.getTotal());
    }

    private String thereIsProduct(String name, String desc, BigDecimal price) {
        return catalog.addProduct(name, desc, price);
    }

    @Test
    void itAllowsAcceptOffer(){
        String productId = thereIsProduct("Example", "desc", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Wojtek");
        SalesFacade sales = thereIsSaleFacade();

        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1, offer.getItemsCount());
        assertEquals(new BigDecimal("10.0"), offer.getTotal());
    }
}

