package pl.jbujak.ecommerse.catalog.Sales.offering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import static org.assertj.core.api.Assertions.*;

public class OfferCalculatorTest {

    @Test
    void zeroOfferForEmptyItems(){
        OfferCalculaotr offerCalculaotr = new OfferCalculaotr();

        Offer offer = offerCalculaotr.calculate(Collections.emptyList());
        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);

    }
}
