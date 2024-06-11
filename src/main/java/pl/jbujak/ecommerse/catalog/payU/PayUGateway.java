package pl.jbujak.ecommerse.catalog.payU;

import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;

public class PayUGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest of) {
        return null;
    }
}
