package pl.jbujak.creditcard;

import org.junit.jupiter.api.Test;

public class helloTest {
    @Test
    void HelloTest(){
        var name = "Jakub";
        var message = String.format("Hello %s",name);

        System.out.println(message);
    }
    @Test
    void equationTest() {
        int a = 2;
        int b = 3;
        var result = a + b;
        assert (5 == result);
    }
}
