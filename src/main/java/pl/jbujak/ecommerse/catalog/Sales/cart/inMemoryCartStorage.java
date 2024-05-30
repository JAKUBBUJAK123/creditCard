package pl.jbujak.ecommerse.catalog.Sales.cart;

import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;

import java.util.Optional;

public class inMemoryCartStorage {

    public Optional<Cart> findByCustomer(String customerId) {
        return Optional.empty();
    }
}
