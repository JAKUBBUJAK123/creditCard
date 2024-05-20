package pl.jbujak.ecommerse.catalog.Sales.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    Map<String,Integer> productsQty;

    public Cart() {
        this.productsQty= new HashMap<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public void addProduct(String productId) {
        if(!isInCart(productId)){
            addToCart(productId);
        }else{
            increaseQuantity(productId);
        }
    }

    public boolean isEmpty() {
    return productsQty.isEmpty();
    }

    public int getLinesCount() {
        return productsQty.size();
    }
    private boolean isInCart(String productId){
        return productsQty.containsKey(productId);
    }
    private void increaseQuantity(String productId){
        productsQty.put(productId, productsQty.get(productId) +1);
    }
    private void addToCart(String productId){
        productsQty.put(productId,1);
    }


    public List<CartLine> getLines() {
        return productsQty.entrySet()
                .stream()
                .map(es -> new CartLine(es.getKey(), es.getValue()))
                .toList();
    }
}
