package pl.jbujak.ecommerse.catalog.Sales.reservation;

import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;

import java.util.UUID;

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
        return new PaymentDetails("http://spy-gateway", UUID.randomUUID().toString());
    }
}
