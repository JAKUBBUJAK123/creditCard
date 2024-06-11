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

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getMerchantPostIp() {
        return merchantPostIp;
    }

    public void setMerchantPostIp(String merchantPostIp) {
        this.merchantPostIp = merchantPostIp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getExtraOrderId() {
        return extraOrderId;
    }

    public void setExtraOrderId(String extraOrderId) {
        this.extraOrderId = extraOrderId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<ProductU> getProducts() {
        return products;
    }

    public void setProducts(List<ProductU> products) {
        this.products = products;
    }
}
