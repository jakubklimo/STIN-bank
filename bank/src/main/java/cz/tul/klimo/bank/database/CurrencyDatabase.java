package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyDatabase  extends JpaRepository<Currency, Long> {

    Currency findByCode(String code);

}
