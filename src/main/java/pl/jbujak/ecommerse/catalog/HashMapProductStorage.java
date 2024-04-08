package pl.jbujak.ecommerse.catalog;

import java.util.HashMap;
import java.util.List;

public class HashMapProductStorage implements ProductStorage {
    HashMap<String, Product> products;

    public HashMapProductStorage(HashMap<String, Product> products) {
        this.products = products;
    }

    @Override
    public void add(Product newProduct) {

    }

    @Override
    public Product getProductBy(String id) {
        return null;
    }

}