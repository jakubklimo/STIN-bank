package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.database.CurrencyDatabase;
import cz.tul.klimo.bank.entity.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CurrencyService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CurrencyDatabase currencyDatabase;

    private static final String CNB_URL = "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt";
    private static final DecimalFormat DECIMAL_FORMAT;

    static{
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DECIMAL_FORMAT = new DecimalFormat("#,##0.0000", symbols);
    }

    private Date lastUpdated;

    public void createCurrency(Currency currency){
        currencyDatabase.save(currency);
    }

    public Currency getCurrency(String code){
        return currencyDatabase.findByCode(code);
    }

    public void updateKurzy() throws IOException, ParseException {
        dropTable();
        URL url = new URL(CNB_URL);
        try(BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()))){
            String radek;
            r.readLine();
            r.readLine();
            while((radek = r.readLine()) != null){
                String [] sloupce = radek.split("\\|");
                Currency kurz = new Currency();
                kurz.setMnozstvi(Integer.parseInt(sloupce[2]));
                kurz.setCode(sloupce[3]);
                kurz.setKurz(DECIMAL_FORMAT.parse(sloupce[4]).doubleValue());
                createCurrency(kurz);
            }
            this.lastUpdated = new Date();
        }
    }

    public void dropTable() {
        currencyDatabase.deleteAll();
    }

    public Date getLastUpdated(){
        return lastUpdated;
    }

    public void setLastUpdated(Date date){
        this.lastUpdated = date;
    }
}
