package pl.jbujak.ecommerse.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private final String description;
    private BigDecimal price;

    public Product(UUID id, String name, String description) {
        this.id = id.toString();
        this.name = name;
        this.description = description;

    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price.toString();
    }

    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}
