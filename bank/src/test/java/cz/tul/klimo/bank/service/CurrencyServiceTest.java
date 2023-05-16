package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.entity.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    private static final String CNB_URL = "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt";

    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        currencyService = new CurrencyService();
    }

    /*@Test
    void updateKurzy_ShouldUpdateCurrencies() throws IOException, ParseException {
        // Arrange
        URL url = mock(URL.class);
        BufferedReader reader = mock(BufferedReader.class);
        when(url.openStream()).thenReturn(null);
        when(reader.readLine()).thenReturn(null).thenReturn(null).thenReturn("A|Česká republika|CZK|1|1.0000");

        currencyService.setLastUpdated(new Date(System.currentTimeMillis() - 10000));

        // Act
        currencyService.updateKurzy();

        // Assert
        List<Currency> expectedCurrencies = new ArrayList<>();
        Currency currency = new Currency();
        currency.setMnozstvi(1);
        currency.setCode("CZK");
        currency.setKurz(1.0000);
        expectedCurrencies.add(currency);

        assertEquals(expectedCurrencies, currencyService.getKurzy());
        verify(url).openStream();
        verify(reader, times(3)).readLine();
    }*/

    /*@Test
    void getKurz_WithExistingCode_ShouldReturnCurrency() {
        // Arrange
        List<Currency> currencies = new ArrayList<>();
        Currency currency = new Currency();
        currency.setMnozstvi(1);
        currency.setCode("CZK");
        currency.setKurz(1.0000);
        currencies.add(currency);
        currencyService.setLastUpdated(new Date());

        // Act
        Currency result = currencyService.getKurz("CZK");

        // Assert
        assertEquals(currency, result);
    }*/

    @Test
    void getKurz_WithNonExistingCode_ShouldReturnNull() {
        // Arrange
        List<Currency> currencies = new ArrayList<>();
        Currency currency = new Currency();
        currency.setMnozstvi(1);
        currency.setCode("CZK");
        currency.setKurz(1.0000);
        currencies.add(currency);
        currencyService.setLastUpdated(new Date());

        // Act
        Currency result = currencyService.getKurz("USD");

        // Assert
        assertEquals(null, result);
    }

    @Test
    void getLastUpdatedDateTest(){
        Date date = new Date();
        currencyService.setLastUpdated(date);
        Assertions.assertEquals(date, currencyService.getLastUpdated());
    }

    @Test
    void setLastUpdatedTest(){
        Date date = new Date();
        currencyService.setLastUpdated(date);
        Assertions.assertEquals(date, currencyService.getLastUpdated());
    }
}

