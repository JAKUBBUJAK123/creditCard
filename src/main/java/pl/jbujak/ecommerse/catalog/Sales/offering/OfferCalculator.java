package pl.jbujak.ecommerse.catalog.Sales.offering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jbujak.ecommerse.catalog.ProductCatalog;
import pl.jbujak.ecommerse.catalog.Sales.cart.CartLine;
import pl.jbujak.ecommerse.catalog.Product;
import pl.jbujak.ecommerse.catalog.SqlProductStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class OfferCalculator {
    private final ProductCatalog catalog;

    public OfferCalculator(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public Offer calculate(List<CartLine> lines) {
        int quantitySum = 0;
        List<BigDecimal> finalPriceArray = new ArrayList<>();

        for (CartLine cartLine : lines) {
            quantitySum += cartLine.getQty();
            Product product = catalog.getProductBy(cartLine.getProductId());
            BigDecimal productPrice = product.getPrice();
            int nthForFree = 5; // every 5nth product for free
            int quantity = cartLine.getQty() - cartLine.getQty() / nthForFree;
            BigDecimal lineTotal = BigDecimal.valueOf(quantity).multiply(productPrice);
            finalPriceArray.add(lineTotal);
        }

        BigDecimal finalPrice = finalPriceArray.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal finalPriceToReturn;
        if (finalPrice.compareTo(BigDecimal.valueOf(100)) >= 0) {
            finalPriceToReturn = finalPrice.multiply(BigDecimal.valueOf(0.9)); // 10% discount
        } else {
            finalPriceToReturn = finalPrice;
        }
        return new Offer(finalPriceToReturn, quantitySum);
    }

}
