package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountDatabase extends JpaRepository<Account, Long>{
    Account findById(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Accounts e WHERE e.id = :id")
    void deleteById(@Param("id") int id);
}
