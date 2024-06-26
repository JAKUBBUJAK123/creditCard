package pl.jbujak.ecommerse.catalog.payU;

public class OrderCreateResponse {
    String extraOrderId;
    Status status;
    String redirectUri;
    String orderId;

    public String getExtOrderId() {
        return extraOrderId;
    }

    public OrderCreateResponse setExtOrderId(String extOrderId) {
        this.extraOrderId = extOrderId;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public OrderCreateResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }
}
