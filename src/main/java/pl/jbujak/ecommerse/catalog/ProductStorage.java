package pl.jbujak.ecommerse.catalog;

import java.util.List;

public interface ProductStorage {
    void add(Product newProduct);
    void setUpDatabase();

    Product getProductBy(String id);

    List<Product> allProducts();
}
