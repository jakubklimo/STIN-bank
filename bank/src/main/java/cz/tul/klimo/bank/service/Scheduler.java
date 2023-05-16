package cz.tul.klimo.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
@EnableScheduling
public class Scheduler {

    @Autowired
    private CurrencyService currencyService;

    @Scheduled(cron = "0 30 14 * * ?")
    public void task() throws IOException, ParseException {
        currencyService.updateKurzy();
    }

}
