package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.database.TransactionDatabase;
import cz.tul.klimo.bank.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDatabaseService {
    @Autowired
    private TransactionDatabase transactionDatabase;

    public void createTransaction(Transaction transaction){
        transactionDatabase.save(transaction);
    }

    public Transaction getTransactionById(int id){
        return transactionDatabase.findById(id);
    }
}
