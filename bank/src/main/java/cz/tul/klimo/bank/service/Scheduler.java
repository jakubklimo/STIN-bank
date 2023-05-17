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
    CurrencyService currencyService;

    @Scheduled(cron = "0 40 14 * * ?")
    public void executeTask() throws IOException, ParseException {
        currencyService.updateKurzy();
    }
}
