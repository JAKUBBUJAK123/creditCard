package pl.jbujak.ecommerse.catalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {
    @Test
    void isListAvailableProducts() {
        ProductCatalog catalog = new ProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void itAllowsToAddProducts() {
        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(100));
        List<Product> products = catalog.allProducts();

        assertThat(products).hasSize(1);

    }

    @Test
    void itAllowsChangePrice() {
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083" , "Nice one",BigDecimal.valueOf(100));

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }

    @Test
    void itLoadsSingleProductById(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(100));

        Product loaded = catalog.getProductBy(id);

        assertThat(id).isEqualTo(loaded.getId());
    }
}
