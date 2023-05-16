package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDatabase  extends JpaRepository<Transaction, Long> {
    Transaction findById(int id);
}
