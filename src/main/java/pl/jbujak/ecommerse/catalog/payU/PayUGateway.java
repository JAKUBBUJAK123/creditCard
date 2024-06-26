package pl.jbujak.ecommerse.catalog.payU;

import org.springframework.stereotype.Component;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;
public class PayUGateway implements PaymentGateway {
    private final PayU payU;
    public PayUGateway(PayU payU) {
        this.payU = payU;
    }
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return payU.registerPayment(registerPaymentRequest);
    }
}
