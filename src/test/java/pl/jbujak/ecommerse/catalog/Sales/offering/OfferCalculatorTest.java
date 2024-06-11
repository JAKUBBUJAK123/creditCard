package pl.jbujak.ecommerse.catalog.Sales.offering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jbujak.ecommerse.catalog.ProductCatalog;
import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;
import pl.jbujak.ecommerse.catalog.Sales.cart.CartLine;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class OfferCalculatorTest {
    @Autowired
    private OfferCalculator offerCalculator;
    @Autowired
    ProductCatalog catalog;
    @Test
    void itCalculatesOffer(){
        Cart cart = Cart.empty();
        catalog.setUpDatabase();
        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(10));
        catalog.addProduct("Cobi Blocks" , "Nice one" , BigDecimal.valueOf(5));
        String productId1 = catalog.allProducts().get(0).getId();
        String productId2 = catalog.allProducts().get(1).getId();

        for (int i = 0; i < 10; i++) {
            cart.addProduct(productId1);
        }

        for (int i = 0; i < 5; i++) {
            cart.addProduct(productId2);
        }

        List<CartLine> lines = cart.getLines();
        var result = offerCalculator.calculate(lines);

        assertThat(result.getItemsCount())
                .isEqualTo(15);
        assertThat(result.getTotal())
                .asString()
                .isEqualTo("90.00");
    }
    private String thereIsProduct(String id){
        return id;
    }
}
