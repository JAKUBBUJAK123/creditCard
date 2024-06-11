package pl.jbujak.ecommerse.catalog.payU;

public class OrderCreateResponse {
    String extraOrderId;
    Status status;
    String redirectId;
    String orderId;

    public String getExtraOrderId() {
        return extraOrderId;
    }

    public void setExtraOrderId(String extraOrderId) {
        this.extraOrderId = extraOrderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRedirectId() {
        return redirectId;
    }

    public void setRedirectId(String redirectId) {
        this.redirectId = redirectId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
