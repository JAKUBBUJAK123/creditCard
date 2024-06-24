package pl.jbujak.ecommerse.catalog.Sales.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ReservationDetails {

    private String reservationId;
    private String paymentURL;
    private BigDecimal total;

    @JsonCreator
    public ReservationDetails(@JsonProperty("reservationId") String reservationId,
                              @JsonProperty("paymentURL") String paymentURL,
                              @JsonProperty("total") BigDecimal total) {
        this.reservationId = reservationId;
        this.paymentURL = paymentURL;
        this.total = total;
    }

    public ReservationDetails() {
    }

    public ReservationDetails setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return this;
    }

    public ReservationDetails setPaymentURL(String paymentURL) {
        this.paymentURL = paymentURL;
        return this;
    }

    public ReservationDetails setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentURL() {
        return paymentURL;
    }


}
