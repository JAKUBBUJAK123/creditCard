package pl.jbujak.ecommerse.catalog.payU;

import java.lang.reflect.Array;
import java.util.List;

public class Buyer {
    String email;
    String phone;
    String firstName;
    String lastName;
    String language;
    List<ProductU> Products;

    public List<ProductU> getProducts() {
        return Products;
    }

    public void setProducts(List<ProductU> products) {
        Products = products;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguage() {
        return language;
    }
}
