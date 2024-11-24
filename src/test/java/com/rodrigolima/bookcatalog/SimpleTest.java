package com.rodrigolima.bookcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    @Test
    public void testSum() {
        int a = 2;
        int b = 3;
        int sum = a + b;

        assertEquals(5, sum, "A soma deve ser 5");
    }
}
