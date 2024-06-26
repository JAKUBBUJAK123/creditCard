package pl.jbujak.ecommerse.catalog.Sales.Payment;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

}
