package pl.jbujak.ecommerse.catalog.Sales.Cart;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;
import pl.jbujak.ecommerse.catalog.Sales.cart.CartLine;

import java.util.List;

public class CartTest {
    @Test
    void itIsEmptyWhenCreated(){
        Cart cart = Cart.empty();
        assertThat(cart.isEmpty())
                .isTrue();
    }
    @Test
    void itIsEmptyWhenProductWasAdded(){
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.addProduct(productId);

        assertThat(cart.isEmpty())
                .isFalse();
    }

    private String thereIsProduct(String id) {
        return id;
    }
    @Test
    void itExposeUniqueProductCountS1() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        cart.addProduct(productX);

        assertThat(cart.getLinesCount())
                .isEqualTo(1);
    }
    @Test
    void itExposeUniqueProductCountS2() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");
        cart.addProduct(productX);
        cart.addProduct(productX);

        assertThat(cart.getLinesCount())
                .isEqualTo(1);
    }
    @Test
    void itExposeUniqueProductCountS3() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");
        String productZ = thereIsProduct("Z");

        cart.addProduct(productX);
        cart.addProduct(productY);
        cart.addProduct(productX);

        assertThat(cart.getLinesCount())
                .isEqualTo(2);
    }
    @Test
    void itExposeCartLineQuantityS1() {
        Cart cart = Cart.empty();
        String productx = thereIsProduct("X");

        cart.addProduct(productx);
        List<CartLine> lines = cart.getLines();

        assertCartContainsXAmountOfProduct(lines, productx, 1);
    }

    @Test
    void itExposeCartLineQuantityS2() {
        Cart cart = Cart.empty();
        String productx = thereIsProduct("X");

        cart.addProduct(productx);
        cart.addProduct(productx);
        List<CartLine> lines = cart.getLines();

        assertCartContainsXAmountOfProduct(lines, productx, 2);
    }

    @Test
    void itExposeCartLineQuantityS3() {
        Cart cart = Cart.empty();
        String productx = thereIsProduct("X");
        String producty = thereIsProduct("Y");

        cart.addProduct(productx);
        cart.addProduct(productx);
        cart.addProduct(productx);
        cart.addProduct(producty);
        List<CartLine> lines = cart.getLines();

        assertCartContainsXAmountOfProduct(lines, productx, 3);
    }

    private void assertCartContainsXAmountOfProduct(List<CartLine> lines, String productId, int expectedQuantity) {
        assertThat(lines)
                .filteredOn(cartLine -> cartLine.getProductId().equals(productId))
                .extracting(cartLine -> cartLine.getQty())
                .first()
                .isEqualTo(expectedQuantity);
    }
}
