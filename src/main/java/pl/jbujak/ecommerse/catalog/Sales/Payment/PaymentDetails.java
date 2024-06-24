package pl.jbujak.ecommerse.catalog.Sales.Payment;

public class PaymentDetails {
    private final String url;
    private final String Id;

    public PaymentDetails(String url ,String id) {
        this.Id = id;
        this.url = url;
    }

    public String getPaymentUrl() {
        return url;
    }
}
