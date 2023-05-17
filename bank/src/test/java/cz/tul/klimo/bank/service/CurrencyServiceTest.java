package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.database.CurrencyDatabase;
import cz.tul.klimo.bank.entity.Currency;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CurrencyServiceTest {

    private static final String CNB_URL = "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt";

    @Mock
    private EntityManager entityManager;

    @Mock
    private CurrencyDatabase currencyDatabase;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCurrency() {
        Currency currency = new Currency();
        currencyService.createCurrency(currency);
        verify(currencyDatabase, times(1)).save(currency);
    }

    @Test
    public void testGetCurrency() {
        String code = "USD";
        Currency currency = new Currency();
        when(currencyDatabase.findByCode(code)).thenReturn(currency);
        Currency result = currencyService.getCurrency(code);
        verify(currencyDatabase, times(1)).findByCode(code);
        assertEquals(currency, result);
    }

    @Test
    public void testDropTable() {
        currencyService.dropTable();
        verify(currencyDatabase, times(1)).deleteAll();
    }

    @Test
    public void testGetLastUpdated() {
        Date expectedDate = new Date();
        currencyService.setLastUpdated(expectedDate);

        // Verify
        Date result = currencyService.getLastUpdated();
        assertEquals(expectedDate, result);
    }

    @Test
    public void testSetLastUpdated() {
        Date expectedDate = new Date();

        // Execute
        currencyService.setLastUpdated(expectedDate);

        // Verify
        Date result = currencyService.getLastUpdated();
        assertEquals(expectedDate, result);
    }
}


