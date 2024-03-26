package pl.jbujak.ecommerse.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private final String description;
    private BigDecimal price;

    public Product(UUID id, String name, String description, BigDecimal price) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
