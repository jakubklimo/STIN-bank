package cz.tul.klimo.bank.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyTest {
    @Test
    public void testGettersAndSetters() {
        String code = "USD";
        int mnozstvi = 1;
        double kurz = 21.345;

        Currency currency = new Currency();
        currency.setCode(code);
        currency.setMnozstvi(mnozstvi);
        currency.setKurz(kurz);

        Assertions.assertEquals(code, currency.getCode());
        Assertions.assertEquals(mnozstvi, currency.getMnozstvi());
        Assertions.assertEquals(kurz, currency.getKurz());
    }

    @Test
    public void testConstructor() {
        String code = "USD";
        int mnozstvi = 1;
        double kurz = 21.345;

        Currency currency = new Currency(code, mnozstvi, kurz);

        Assertions.assertEquals(code, currency.getCode());
        Assertions.assertEquals(mnozstvi, currency.getMnozstvi());
        Assertions.assertEquals(kurz, currency.getKurz());
    }
}
