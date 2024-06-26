package pl.jbujak.ecommerse.catalog.payU;

import pl.jbujak.ecommerse.catalog.Product;

import java.util.List;

public class OrderCreateRequest {
    String notifyUrl;
    String customerIp;
    String merchantPostIp;
    String description;
    String currencyCode;
    Integer totalAmount;
    String extraOrderId;
    Buyer buyer;
    List<ProductU> products;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public OrderCreateRequest setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public OrderCreateRequest setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
        return this;
    }

    public String getMerchantPosId() {
        return merchantPostIp;
    }

    public OrderCreateRequest setMerchantPosId(String merchantPosId) {
        this.merchantPostIp= merchantPosId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderCreateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public OrderCreateRequest setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public OrderCreateRequest setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getExtOrderId() {
        return extraOrderId;
    }

    public OrderCreateRequest setExtOrderId(String extOrderId) {
        this.extraOrderId = extOrderId;
        return this;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public OrderCreateRequest setBuyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public List<ProductU> getProducts() {
        return products;
    }

    public OrderCreateRequest setProducts(List<ProductU> products) {
        this.products = products;
        return this;
    }
}
