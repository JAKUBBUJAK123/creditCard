package pl.jbujak.ecommerse.catalog.Sales.cart;

import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class inMemoryCartStorage {
    Map<String, Cart> carts;

    public inMemoryCartStorage(){
        this.carts = new HashMap<>() ;
    }
    public Optional<Cart> findByCustomer(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    public void save(String customerId, Cart cart) {
        carts.put(customerId,cart);
    }
}
