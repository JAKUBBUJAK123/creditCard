package pl.jbujak.ecommerse.catalog.Sales.reservation;

import pl.jbujak.ecommerse.catalog.Sales.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;
    public Integer getRequestCount() {
    return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
