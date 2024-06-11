package pl.jbujak.ecommerse.catalog.Sales.Payment;

public class PaymentDetails {
    private final String url;

    public PaymentDetails(String url) {

        this.url = url;
    }

    public String getPaymentUrl() {
        return url;
    }
}
