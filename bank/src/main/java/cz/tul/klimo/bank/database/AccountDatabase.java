package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDatabase extends JpaRepository<Account, Long>{
    Account findById(int id);

}
