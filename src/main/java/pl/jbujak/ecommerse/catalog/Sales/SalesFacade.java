package pl.jbujak.ecommerse.catalog.Sales;

import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.Offer;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculaotr;
import pl.jbujak.ecommerse.catalog.Sales.order.ReservationDetails;


public class SalesFacade {
    private inMemoryCartStorage cartStorage;
    private OfferCalculaotr Calculator;
    public SalesFacade(inMemoryCartStorage cartStorage , OfferCalculaotr calculator) {
        this.cartStorage = cartStorage;
        this.Calculator = calculator;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);
        return OfferCalculaotr.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }



    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }
}
