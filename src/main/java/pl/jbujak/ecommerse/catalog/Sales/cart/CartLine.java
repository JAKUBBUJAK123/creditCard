package pl.jbujak.ecommerse.catalog.Sales.cart;

public class CartLine {


    private final String productId;
    private final Integer qty;

    public CartLine(String productId, Integer Qty) {
        this.productId = productId;
        qty = Qty;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQty() {
        return qty;
    }
}
