package pl.jbujak.ecommerse.catalog.Sales.order;

import java.math.BigDecimal;

public class ReservationDetails {
    private final String reservationId;
    private final String paymentUrl;
    public ReservationDetails(String reservationId, String paymentUrl) {
        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;

    }

    public BigDecimal getTotal() {
        return BigDecimal.ZERO;
    }

    public String getReservationId() {
    return reservationId;
    }

    //public String getPayment() {
    //return "http://example-product";
    //}
    public String getPaymentUrl(){
        return paymentUrl;
    }


}
