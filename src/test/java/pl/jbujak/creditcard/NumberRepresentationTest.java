package pl.jbujak.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class NumberRepresentationTest {
    @Test
    void doubleTest(){
        double a = 0.02;
        double b = 0.03;
        System.out.println("Double");
        System.out.println(b-a);
    }
    @Test
    void FLoatTest(){
        float a = 0.002f;
        float b = 0.003f;
        System.out.println("Float");
        System.out.println(b-a);
    }
    @Test
    void BigDecimalTest(){
        BigDecimal a = new BigDecimal(0.0002);
        BigDecimal b = new BigDecimal(0.0003);
        System.out.println("Bigdecimal");
        System.out.println(a.subtract(b));
    }
}
