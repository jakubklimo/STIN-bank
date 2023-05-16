package cz.tul.klimo.bank.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenerateCodeTest {
    @Test
    public void testGetRandomNumberString() {
        String randomNumberString = GenerateCode.getRandomNumberString();
        Assertions.assertNotNull(randomNumberString);
        Assertions.assertEquals(6, randomNumberString.length());

        // Testování, zda se vrácený řetězec skládá pouze z číslic
        Assertions.assertTrue(randomNumberString.matches("\\d+"));
    }
}