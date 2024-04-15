package pl.jbujak.ecommerse.catalog;

import java.util.HashMap;
import java.util.List;

public class HashMapProductStorage implements ProductStorage {
    HashMap<String, Product> products;

    public HashMapProductStorage(){
        products = new HashMap<>();
    }

    @Override
    public void add(Product newProduct) {
        products.put(newProduct.getId(),newProduct);

    }

    @Override
    public Product getProductBy(String id) {
        return products.get(id);
    }

    @Override
    public List<Product> allProducts() {
        return products.values().stream().toList();
    }

}

