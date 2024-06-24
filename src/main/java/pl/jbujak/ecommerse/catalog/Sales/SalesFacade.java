package pl.jbujak.ecommerse.catalog.Sales;

import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;
import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.AcceptOfferRequest;
import pl.jbujak.ecommerse.catalog.Sales.offering.Offer;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculator;
import pl.jbujak.ecommerse.catalog.Sales.order.ReservationDetails;
import pl.jbujak.ecommerse.catalog.Sales.reservation.Reservation;
import pl.jbujak.ecommerse.catalog.Sales.reservation.ReservationRepository;

import java.util.UUID;


public class SalesFacade {
    private inMemoryCartStorage cartStorage;
    private OfferCalculator Calculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(inMemoryCartStorage cartStorage , OfferCalculator calculator, PaymentGateway paymentGateway , ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.Calculator = calculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
        return Calculator.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId,acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(reservationId, customerId, acceptOfferRequest, offer ,paymentDetails);
        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId , paymentDetails.getPaymentUrl() , offer.getTotal());
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
        cartStorage.save(customerId, cart);
    }



    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }
}
