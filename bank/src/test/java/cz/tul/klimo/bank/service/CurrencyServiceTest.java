package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.entity.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.*;

public class CurrencyServiceTest {
    private static final String TEST_URL = "https://www.example.com/test.txt";

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private URL url;

    @Mock
    private BufferedReader bufferedReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyService();
    }

    @Test
    public void testUpdateKurzy() throws IOException, ParseException {
        when(url.openStream()).thenReturn(new URL(TEST_URL).openStream());
        when(bufferedReader.readLine())
                .thenReturn("Header")
                .thenReturn("Subheader")
                .thenReturn("1|USD|1|US Dollar|21.345");
        currencyService.updateKurzy();

        List<Currency> currencies = currencyService.getKurzy();
        Assertions.assertEquals(1, currencies.size());

        Currency currency = currencies.get(0);
        Assertions.assertEquals(1, currency.getMnozstvi());
        Assertions.assertEquals("USD", currency.getCode());
        Assertions.assertEquals(21.345, currency.getKurz());
    }

    @Test
    public void testGetKurz() {
        Currency currency1 = new Currency("USD", 1, 21.345);
        Currency currency2 = new Currency("EUR", 1, 25.678);
        currencyService.getKurzy().add(currency1);
        currencyService.getKurzy().add(currency2);

        Currency retrievedCurrency = currencyService.getKurz("usd");
        Assertions.assertEquals(currency1, retrievedCurrency);

        retrievedCurrency = currencyService.getKurz("eur");
        Assertions.assertEquals(currency2, retrievedCurrency);

        retrievedCurrency = currencyService.getKurz("gbp");
        Assertions.assertNull(retrievedCurrency);
    }

    @Test
    public void testGetLastUpdated() {
        Date date = new Date();
        currencyService.setLastUpdated(date);

        Date retrievedDate = currencyService.getLastUpdated();
        Assertions.assertEquals(date, retrievedDate);
    }
}

