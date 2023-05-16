package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDatabaseService {

    @Autowired
    private AccountDatabase accountDatabase;

    public void createAccount(Account account){
        accountDatabase.save(account);
    }

    public Account getAccountById(int id){
        return accountDatabase.findById(id);
    }
}
