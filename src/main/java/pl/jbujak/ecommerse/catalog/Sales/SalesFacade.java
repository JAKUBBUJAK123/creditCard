package pl.jbujak.ecommerse.catalog.Sales;

public class SalesFacade {
    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }

    public void addToCart(String customerId, String productId) {

    }
}
