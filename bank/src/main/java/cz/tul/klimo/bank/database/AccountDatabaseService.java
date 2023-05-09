package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDatabaseService {

    @Autowired
    private AccountDatabase accountDatabase;

    public Account createAccount(Account account){
        return accountDatabase.save(account);
    }

    public Account getAccountById(int id){
        return accountDatabase.findById(id);
    }
}
